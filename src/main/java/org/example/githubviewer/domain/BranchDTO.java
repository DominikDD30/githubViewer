package org.example.githubviewer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BranchDTO {
    private String name;
    private String lastCommitSha;
}
