package org.example.githubviewer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.githubviewer.domain.Branch;
import org.example.githubviewer.domain.Repository;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class GithubApiClient {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    public List<Repository> fetchRepositories(String username) throws RuntimeException, IOException {
        String repoPath = String.format("https://api.github.com/users/%s/repos", username);

        Type type = new TypeToken<List<Repository>>() {}.getType();
        List<Repository> repositories = executeRequest(repoPath, type);
        if (Objects.isNull(repositories))
            throw new RuntimeException("Not found any repositories for user: " + username);
        return repositories;
    }

    public List<Branch> fetchBranches(Repository repository) throws IOException {
        String branchesPath = String.format("https://api.github.com/repos/%s/%s/branches",
                repository.getOwner().getLogin(), repository.getName());

        Type type = new TypeToken<List<Branch>>() {}.getType();
        return executeRequest(branchesPath, type);
    }

    private  <T> T executeRequest(String url, Type type) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), type);
        }catch (RuntimeException e){
            return null;
        }
    }
}
