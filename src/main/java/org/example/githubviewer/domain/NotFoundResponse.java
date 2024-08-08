package org.example.githubviewer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NotFoundResponse {
    private final int status=404;
    private String message;

    public NotFoundResponse(String message) {
        this.message = message;
    }
}
