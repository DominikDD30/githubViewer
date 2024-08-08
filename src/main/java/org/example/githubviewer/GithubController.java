package org.example.githubviewer;

import lombok.AllArgsConstructor;
import org.example.githubviewer.domain.NotFoundResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GithubController {

    private SearchService searchService;

    @GetMapping("/{username}")
    public ResponseEntity<Object> getGithubRepositories(@PathVariable String username) {
        try {
            return ResponseEntity.ok(searchService.getUserRepositoriesData(username));
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new NotFoundResponse(e.getMessage()));
        }
    }
}
