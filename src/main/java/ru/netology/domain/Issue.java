package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    private int id;
    private String name;
    private String body;
    private String author;
    private int daysSinceOpening;
    private boolean isClosed;
    private Set<String> label;
    private String assignee;
}

