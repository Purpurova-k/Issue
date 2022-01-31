package ru.netology.util;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class ReverseDateComparator implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getDaysSinceOpening() - o1.getDaysSinceOpening();
    }
}
