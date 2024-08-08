package org.example.githubviewer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Branch {
    private String name;
    private Commit commit;


}
