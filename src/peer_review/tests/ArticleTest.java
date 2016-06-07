package peer_review.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import peer_review.builders.*;
import peer_review.models.Article;
import peer_review.models.Researcher;

public class ArticleTest {
	Article article;

	@Before
	public void setUp() throws Exception {
		article = new ArticleBuilder().id(1).title("title").build();
	}

	@Test
	public void testreviewers() {
		Researcher reviewer1 = new ResearcherBuilder().id(1).build();
		Researcher reviewer2 = new ResearcherBuilder().id(2).build();
		article.addReview(reviewer1, Optional.of(0.0f));
		article.addReview(reviewer2, Optional.of(0.0f));

		assertTrue(article.reviewers().size() == 2);
	}

	@Test
	public void testID() {
		assertTrue(article.getID() == 1);
	}

	@Test
	public void testAuthor() {
		assertTrue(article.getAuthorUniversity().getName() == "UFRGS");
	}

	@Test
	public void testResearchTopic() {
		assertTrue(article.getResearchTopic().getName() == "topic 1");
	}
	
	@Test
	public void testTitle() {
		assertTrue(article.getTitle() == "title");
	}

	@Test
	public void testGrade() {
		float epsilon = 0.0001f;
		Article gradedArticle = new ArticleBuilder().
				grade(new ResearcherBuilder().build(), 3.0f).
				grade(new ResearcherBuilder().build(), 2.0f).
				grade(new ResearcherBuilder().build(), 1.0f).build();

		assertTrue(gradedArticle.getGrades().size() == 3);
		assertTrue(Math.abs(gradedArticle.getGradeAverage() - 2.0f) < epsilon);

		// Test adding another grade
		gradedArticle.addReview(new ResearcherBuilder().build(), Optional.of(0.0f));
		assertTrue(Math.abs(gradedArticle.getGradeAverage() - 1.5f) < epsilon);
	}

}