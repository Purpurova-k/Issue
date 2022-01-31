package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.util.NotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class IssueRepository {

    private List<Issue> issues = new ArrayList<>();

    // Добавить issue
    public void addIssue(Issue issue) {
        issues.add(issue);
    }


    // Показать список открытых issue
    public List<Issue> getOpenIssues() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (!issue.isClosed()) {
                result.add(issue);
            }
        }
        return result;
    }


    // Показать список закрытых issue
    public List<Issue> getCloseIssues() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.isClosed()) {
                result.add(issue);
            }
        }
        return result;
    }


    // Показать список issue, отфильтрованный по автору
    public List<Issue> filterIssuesByAuthor(String author) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAuthor().equals(author)) {
                result.add(issue);
            }
        }
        return result;
    }


    // Показать список issue, отфильтрованный по тегу
    public List<Issue> filterIssuesByLabel(Set<String> label) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getLabel().equals(label)) {
                result.add(issue);
            }
        }
        return result;
    }


    // Показать список issue, отфильтрованный по назначенному исполнителю
    public List<Issue> filterIssuesByAssignee(String assignee) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAssignee().equals(assignee)) {
                result.add(issue);
            }
        }
        return result;
    }


    // Найти все issue
    public List<Issue> findAll() {
        return issues;
    }


    // Найти issue по id
    public Issue findById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }


    // Закрытие issue по id
    public void closeIssue(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Issue with id " + id + "is not found");
        }
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setClosed(true);
            }
        }
    }


    // Открытие issue по id
    public void openIssue(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Issue with id " + id + "is not found");
        }
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setClosed(false);
            }
        }
    }

    // Вспомогательный метод - добавить все
    public void saveAll(List<Issue> newIssues) {
        issues.addAll(newIssues);
    }
}
