package org.example.githubviewer.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Repository {

    private String name;
    private Owner owner;
    private Boolean fork;
}
