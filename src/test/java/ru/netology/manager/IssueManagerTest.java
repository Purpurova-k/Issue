package ru.netology.manager;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class IssueManagerTest {

    private IssueRepository repo = Mockito.mock(IssueRepository.class);
    private IssueManager manager = new IssueManager(repo);

    private HashSet<String> label1 = new HashSet<>(Collections.singleton("status: new"));
    private HashSet<String> label2 = new HashSet<>(Collections.singleton("status: in progress"));
    private HashSet<String> label3 = new HashSet<>(Collections.singleton("status: waiting-for-feedback"));


    Issue first = new Issue(1, "Issue1", "John", 10, false, label1, "Ivan");
    Issue second = new Issue(2, "Issue2", "Kate", 1, false, label2, "Julia");
    Issue third = new Issue(3, "Issue3", "Bob", 100, true, label3, "Mary");
    Issue forth = new Issue(4, "Issue4", "Ivan", 99, true, label3, "Mary");
    Issue fifth = new Issue(5, "Issue5", "John", 2, false, label1, "Kate");
    Issue sixth = new Issue(6, "Issue6", "Julia", 5, false, label2, "Mary");


    @Test
    public void shouldAddIssue() {
        List<Issue> returned = List.of(first, second);
        doReturn(returned).when(repo).findAll();

        manager.addIssue(first);
        manager.addIssue(second);

        List<Issue> expected = List.of(first, second);
        List<Issue> actual = manager.getAll();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).findAll();
    }


    @Test
    public void shouldAddAllIssues() {
        List<Issue> returned = List.of(first, second, third, forth, fifth, sixth);
        doReturn(returned).when(repo).findAll();

        manager.addAll(returned);

        List<Issue> expected = List.of(first, second, third, forth, fifth, sixth);
        List<Issue> actual = manager.getAll();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).findAll();
    }


    @Test
    public void shouldFindOpenedIssues() {
        List<Issue> returned = List.of(first, second);
        doReturn(returned).when(repo).getOpenIssues();

        List<Issue> expected = List.of(first, second);
        List<Issue> actual = manager.getOpenIssues();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).getOpenIssues();
    }


    @Test
    public void shouldFindClosedIssues() {
        List<Issue> returned = List.of(third, forth);
        doReturn(returned).when(repo).getCloseIssues();

        List<Issue> expected = List.of(third, forth);
        List<Issue> actual = manager.getCloseIssues();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).getCloseIssues();
    }


    @Test
    public void shouldFilterByAuthor() {
        List<Issue> returned = List.of(first, fifth);
        doReturn(returned).when(repo).filterIssuesByAuthor("John");

        List<Issue> expected = List.of(first, fifth);
        List<Issue> actual = manager.filterIssuesByAuthor("John");

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterIssuesByAuthor("John");
    }


    @Test
    public void shouldFilterByLabel() {
        List<Issue> returned = List.of(first, fifth);
        doReturn(returned).when(repo).filterIssuesByLabel(label1);

        List<Issue> expected = List.of(first, fifth);
        List<Issue> actual = manager.filterIssuesByLabel(label1);

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterIssuesByLabel(label1);
    }


    @Test
    public void shouldFilterByAssignee() {
        List<Issue> returned = List.of(third, forth, sixth);
        doReturn(returned).when(repo).filterIssuesByAssignee("Mary");

        List<Issue> expected = List.of(third, forth, sixth);
        List<Issue> actual = manager.filterIssuesByAssignee("Mary");

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterIssuesByAssignee("Mary");
    }


    @Test
    public void shouldSortByDate() {
        List<Issue> returned = List.of(first, second, third, forth, fifth, sixth);
        doReturn(returned).when(repo).findAll();

        List<Issue> expected = List.of(second, fifth, sixth, first, forth, third);

        List<Issue> actual = manager.sortIssuesByDate();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).findAll();
    }


    @Test
    public void shouldSortByDateReverse() {
        List<Issue> returned = List.of(first, second, third, forth, fifth, sixth);
        doReturn(returned).when(repo).findAll();

        List<Issue> expected = List.of(third, forth, first, sixth, fifth, second);

        List<Issue> actual = manager.sortIssuesByDateReverse();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).findAll();
    }


    @Test
    public void shouldCloseIssue() {
        List<Issue> returned = List.of(first);
        doReturn(returned).when(repo).getCloseIssues();

        manager.closeIssue(1);

        List<Issue> expected = List.of(first);
        List<Issue> actual = manager.getCloseIssues();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).getCloseIssues();
    }


    @Test
    public void shouldOpenIssue() {
        List<Issue> returned = List.of(third);
        doReturn(returned).when(repo).getOpenIssues();

        manager.openIssue(3);

        List<Issue> expected = List.of(third);
        List<Issue> actual = manager.getOpenIssues();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).getOpenIssues();
    }
}