package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import ru.netology.domain.Issue;
import ru.netology.util.NotFoundException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssueRepositoryTest {
    IssueRepository repo = new IssueRepository();

    private HashSet<String> label1 = new HashSet<>(Collections.singleton("status: new"));
    private HashSet<String> label2 = new HashSet<>(Collections.singleton("status: in progress"));
    private HashSet<String> label3 = new HashSet<>(Collections.singleton("status: waiting-for-feedback"));


    Issue first = new Issue(1, "Issue1", "John", 10, false, label1, "Ivan");
    Issue second = new Issue(2, "Issue2", "Kate", 1, false, label2, "Julia");
    Issue third = new Issue(3, "Issue3", "Bob", 100, true, label3, "Mary");
    Issue forth = new Issue(4, "Issue4", "Ivan", 99, true, label3, "Mary");
    Issue fifth = new Issue(5, "Issue5", "John", 2, false, label1, "Kate");
    Issue sixth = new Issue(6, "Issue6", "Julia", 5, false, label2, "Mary");



    @Nested
    @DisplayName("Tests for Empty List")
    public class Empty {

        @Test
        public void shouldNotFindOpenedIssuesEmptyList() {
            List<Issue> expected = List.of();
            List<Issue> actual = repo.getOpenIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFindClosedIssuesEmptyList() {
            List<Issue> expected = List.of();
            List<Issue> actual = repo.getCloseIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFilterIssuesByAuthorEmptyList() {
            List<Issue> expected = List.of();
            List<Issue> actual = repo.filterIssuesByAuthor("Bob");

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFilterIssuesByLabelEmptyList() {
            List<Issue> expected = List.of();
            List<Issue> actual = repo.filterIssuesByLabel(label1);

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFilterIssuesByAssigneeEmptyList() {
            List<Issue> expected = List.of();
            List<Issue> actual = repo.filterIssuesByAssignee("Mary");

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFindAllIssuesEmptyList() {
            List<Issue> expected = List.of();
            List<Issue> actual = repo.findAll();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFindIssueByIdEmptyList() {
            assertNull(repo.findById(1));
        }


        @Test
        public void shouldNotCloseIssueEmptyList() {
            assertThrows(NotFoundException.class, () -> repo.closeIssue(1));
        }


        @Test
        public void shouldNotOpenIssueEmptyList() {
            assertThrows(NotFoundException.class, () -> repo.openIssue(1));
        }
    }


    @Nested
    @DisplayName("Tests for List with Single Element")
    public class SingleElement {


        @Test
        public void shouldFindOpenedIssuesSingleElement() {
            repo.addIssue(first);
            List<Issue> expected = List.of(first);
            List<Issue> actual = repo.getOpenIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFindClosedIssuesSingleElement() {
            repo.addIssue(third);
            List<Issue> expected = List.of(third);
            List<Issue> actual = repo.getCloseIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFilterIssuesByAuthorSingleElement() {
            repo.addIssue(first);
            List<Issue> expected = List.of(first);
            List<Issue> actual = repo.filterIssuesByAuthor("John");

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFilterIssuesByAuthorSingleElement() {
            repo.addIssue(first);
            List<Issue> expected = List.of();
            List<Issue> actual = repo.filterIssuesByAuthor("Bob");

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFilterIssuesByLabelSingleElement() {
            repo.addIssue(second);
            List<Issue> expected = List.of(second);
            List<Issue> actual = repo.filterIssuesByLabel(label2);

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFilterIssuesByLabelSingleElement() {
            repo.addIssue(second);
            List<Issue> expected = List.of();
            List<Issue> actual = repo.filterIssuesByLabel(label1);

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFilterIssuesByAssigneeSingleElement() {
            repo.addIssue(third);
            List<Issue> expected = List.of(third);
            List<Issue> actual = repo.filterIssuesByAssignee("Mary");

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFilterIssuesByAssigneeSingleElement() {
            repo.addIssue(third);
            List<Issue> expected = List.of();
            List<Issue> actual = repo.filterIssuesByAssignee("Ivan");

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFindAllIssuesSingleElement() {
            repo.addIssue(first);
            List<Issue> expected = List.of(first);
            List<Issue> actual = repo.findAll();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldNotFindIssueByIdSingleElement() {
            repo.addIssue(second);
            assertNull(repo.findById(1));
        }


        @Test
        public void shouldFindIssueByIdSingleElement() {
            repo.addIssue(second);

            Issue expected = second;
            Issue actual = repo.findById(2);

            assertEquals(expected, actual);
        }


        @Test
        public void shouldCloseIssueSingleElement() {
            repo.addIssue(first);
            repo.closeIssue(1);

            List<Issue> expected = List.of(first);
            List<Issue> actual = repo.getCloseIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldOpenIssueSingleElement() {
            repo.addIssue(third);
            repo.openIssue(3);

            List<Issue> expected = List.of(third);
            List<Issue> actual = repo.getOpenIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }
    }


    @Nested
    @DisplayName("Tests for List with Multiple Elements")
    public class MultipleElement {


        @BeforeEach
        void setUp() {
            repo.saveAll(List.of(first, second, third, forth, fifth, sixth));
        }


        @Test
        public void shouldFindOpenedIssuesMultipleElements() {
            List<Issue> expected = List.of(first, second, fifth, sixth);
            List<Issue> actual = repo.getOpenIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFindClosedIssuesMultipleElements() {
            List<Issue> expected = List.of(third, forth);
            List<Issue> actual = repo.getCloseIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFilterIssuesByAuthorMultipleElements() {
            List<Issue> expected = List.of(first, fifth);
            List<Issue> actual = repo.filterIssuesByAuthor("John");

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFilterIssuesByLabelMultipleElements() {
            List<Issue> expected = List.of(second, sixth);
            List<Issue> actual = repo.filterIssuesByLabel(label2);

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFilterIssuesByAssigneeMultipleElements() {
            List<Issue> expected = List.of(third, forth, sixth);
            List<Issue> actual = repo.filterIssuesByAssignee("Mary");

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFindAllIssuesMultipleElements() {
            List<Issue> expected = List.of(first, second, third, forth, fifth, sixth);
            List<Issue> actual = repo.findAll();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldFindIssueByIdMultipleElements() {
            Issue expected = fifth;
            Issue actual = repo.findById(5);

            assertEquals(expected, actual);

        }


        @Test
        public void shouldCloseIssueMultipleElements() {
            repo.closeIssue(1);
            repo.closeIssue(2);

            List<Issue> expected = List.of(first, second, third, forth);
            List<Issue> actual = repo.getCloseIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        public void shouldOpenIssueMultipleElements() {
            repo.openIssue(3);

            List<Issue> expected = List.of(first, second, third, fifth, sixth);
            List<Issue> actual = repo.getOpenIssues();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }
    }

}