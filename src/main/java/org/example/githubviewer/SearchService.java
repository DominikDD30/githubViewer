package org.example.githubviewer;

import lombok.RequiredArgsConstructor;
import org.example.githubviewer.domain.BranchDTO;
import org.example.githubviewer.domain.Repository;
import org.example.githubviewer.domain.RepositoryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final GithubApiClient githubApiClient;

    public List<RepositoryDTO> getUserRepositoriesData(String username) throws Exception {
        List<RepositoryDTO> results = new ArrayList<>();

        List<Repository> repositories = githubApiClient.fetchRepositories(username)
                .stream().filter(repository -> !repository.getFork()).toList();

        for (Repository repository : repositories) {
            results.add(RepositoryDTO.builder()
                    .name(repository.getName())
                    .ownerLogin(repository.getOwner().getLogin())
                    .branches(githubApiClient.fetchBranches(repository).stream()
                            .map(branch -> new BranchDTO(branch.getName(), branch.getCommit().getSha()))
                            .toList())
                    .build());
        }
        return results;
    }


}
