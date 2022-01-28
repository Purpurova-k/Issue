package ru.netology.util;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class DateComparator implements Comparator<Issue> {


    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getDaysSinceOpening() - o2.getDaysSinceOpening();
    }
}
