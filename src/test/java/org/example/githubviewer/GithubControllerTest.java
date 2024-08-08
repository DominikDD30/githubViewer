package org.example.githubviewer;

import org.example.githubviewer.domain.BranchDTO;
import org.example.githubviewer.domain.NotFoundResponse;
import org.example.githubviewer.domain.RepositoryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubControllerTest {

    @Mock
    private SearchService searchService;


    @InjectMocks
    private GithubController githubController;

    @Test
    void thatGetRepositoriesForExistingUserWorkCorrectly() throws Exception {
        //given
        String username = "testuser";
        RepositoryDTO repository1 = new RepositoryDTO(
                "Repo1",
                username,
                List.of(new BranchDTO("main", "c3e81a256727yte17cc55b09cdaf661c27d2f4a1")));


        when(searchService.getUserRepositoriesData(username))
                .thenReturn(List.of(repository1));

        //when
        ResponseEntity<Object> result = githubController.getGithubRepositories(username);
        //then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(result.getBody(), List.of(repository1));
    }

    @Test
    void thatGetRepositoriesShouldFailForNonExistingUser() throws Exception {
        //given
        String username = "otherUser";

        when(searchService.getUserRepositoriesData(username))
                .thenThrow(Exception.class);

        //when
        ResponseEntity<Object> result = githubController.getGithubRepositories(username);
        //then
        Assertions.assertTrue(result.getStatusCode().is4xxClientError());
        Assertions.assertEquals(NotFoundResponse.class, result.getBody().getClass());
    }

}