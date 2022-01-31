package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;
import ru.netology.util.DateComparator;
import ru.netology.util.ReverseDateComparator;

import java.util.*;

public class IssueManager {

    private IssueRepository repo = new IssueRepository();


    public IssueManager (IssueRepository repo) {
        this.repo = repo;
    }

    // Добавление issue
    public void addIssue(Issue issue) {
        repo.addIssue(issue);
    }


    // Список открытых issue
    public List<Issue> getOpenIssues() {
        return repo.getOpenIssues();
    }


    // Список закрытых issue
    public List<Issue> getCloseIssues() {
        return repo.getCloseIssues();
    }


    // Отфильтровать по автору
    public List<Issue> filterIssuesByAuthor(String author) {
        return repo.filterIssuesByAuthor(author);
    }


    // Отфильтровать по тегу
    public List<Issue> filterIssuesByLabel(Set<String> label) {
        return repo.filterIssuesByLabel(label);
    }


    // Отфильтровать по исполнителю
    public List<Issue> filterIssuesByAssignee(String assignee) {
        return repo.filterIssuesByAssignee(assignee);
    }


    // Отсортировать по дате от новых к более старым
    public List<Issue> sortIssuesByDate() {
        List<Issue> result = repo.findAll();
        Comparator dateComparator = new DateComparator();
        Collections.sort(result, dateComparator);
        return result;
        }


    // Отсортировать по дате от старых к более новым
    public List<Issue> sortIssuesByDateReverse() {
        List<Issue> result = repo.findAll();
        Comparator reverseDateComparator = new ReverseDateComparator();
        Collections.sort(result, reverseDateComparator);
        return result;
    }


    // Закрыть issue
    public void closeIssue(int id) {
        repo.closeIssue(id);
    }


    // Открыть issue
    public void openIssue(int id) {
        repo.openIssue(id);
    }

}
