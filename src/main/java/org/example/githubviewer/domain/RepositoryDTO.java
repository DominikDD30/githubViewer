package org.example.githubviewer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RepositoryDTO {
    private String name;
    private String ownerLogin;
    private List<BranchDTO> branches;
}
