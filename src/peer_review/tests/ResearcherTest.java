package peer_review.tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import peer_review.builders.ArticleBuilder;
import peer_review.models.Article;
import peer_review.builders.ResearcherBuilder;
import peer_review.models.Researcher;

public class ResearcherTest {
	Researcher researcher;
	Article article;

	@Before
	public void setUp() throws Exception {
		researcher = new ResearcherBuilder().name("name 1").build();
		System.out.print(researcher.toString());
		article = new ArticleBuilder().id(1).title("title").build();
		System.out.print(article.toString());
		researcher.allocateArticle(article);
	}

	@Test
	public void testGetResearchTopics() {
		assertTrue("topic 1" == researcher.getResearchTopics().get(0).getName());
		assertTrue("topic 2" == researcher.getResearchTopics().get(1).getName());

	}

	@Test
	public void testGetUniversity() {
		assertTrue(researcher.getUniversity().getName() == "UFRGS");

	}

	@Test
	public void testGetName() {
		assertTrue("name 1" == researcher.getName());
	}

	@Test
	public void testGetID() {
		assertTrue(researcher.getID() == 1);
	}

	@Test
	public void testGetAlocatedArticles() {
		assertTrue(1 == researcher.getAlocatedArticles().get(0).getID());
	}
	
	@Test
	public void testIsEligibleToReview() {
		System.out.println("start");
		System.out.println(researcher);
		System.out.println(article);
		assertFalse(researcher.isEligibleToReview(article));
	}
}
