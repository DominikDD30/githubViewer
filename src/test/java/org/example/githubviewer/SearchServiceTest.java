package org.example.githubviewer;

import org.example.githubviewer.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private GithubApiClient githubApiClient;

    @InjectMocks
    private SearchService searchService;

    @Test
    void getUserRepositoriesCorrectly() throws Exception {
        String username = "testuser";

        Repository repository1 = new Repository("Repo1", new Owner("testuser"), false);
        Repository repository2 = new Repository("Repo2", new Owner("testuser"), false);


        Branch branch1 = new Branch("main", new Commit("c3e81a256727yte17cc55b09cdaf661c27d2f4a1"));
        Branch branch2 = new Branch("develop", new Commit("dba025bcf94ybc19f1cc15d08af16a19a043c69d"));
        Branch branch3 = new Branch("main", new Commit("d4f92b257728yte18cc55c10ceaf662c27e3f4b2"));

        List<Repository> repositories = List.of(repository1, repository2);

        when(githubApiClient.fetchRepositories(username)).thenReturn(repositories);
        when(githubApiClient.fetchBranches(repository1)).thenReturn(List.of(branch1, branch2));
        when(githubApiClient.fetchBranches(repository2)).thenReturn(List.of(branch3));

        List<RepositoryDTO> result = searchService.getUserRepositoriesData(username);

        assertEquals(2, result.size());
        assertEquals("Repo1", result.getFirst().getName());
        assertEquals("testuser", result.getFirst().getOwnerLogin());
        assertEquals(2, result.get(0).getBranches().size());
        assertEquals("develop", result.get(0).getBranches().get(1).getName());

        assertEquals("Repo2", result.get(1).getName());
        assertEquals("testuser", result.get(1).getOwnerLogin());
        assertEquals(1, result.get(1).getBranches().size());
        assertEquals("main", result.get(1).getBranches().getFirst().getName());
    }

}