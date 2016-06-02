package peer_review.tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import peer_review.builders.*;
import peer_review.models.Article;

public class ArticleTest {
	Article article;

	@Before
	public void setUp() throws Exception {
		article = new ArticleBuilder().id(1).title("title").build();
		System.out.print(article.toString());
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
		gradedArticle.setGrade(new ResearcherBuilder().build(), 0);
		assertTrue(Math.abs(gradedArticle.getGradeAverage() - 1.5f) < epsilon);
	}

}