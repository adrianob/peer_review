package peer_review.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import peer_review.models.Article;
import peer_review.models.Conference;
import peer_review.models.Researcher;
import peer_review.models.University;
import peer_review.builders.*;

public class ConferenceTest {

	@Test
	public void testsortReviewers() {
		Researcher researcher1 = new ResearcherBuilder().id(1).build();
		researcher1.allocateArticle(new ArticleBuilder().build());
		Researcher researcher2 = new ResearcherBuilder().id(2).build();
		researcher2.allocateArticle(new ArticleBuilder().build());
		Researcher researcher3 = new ResearcherBuilder().id(3).build();
		researcher3.allocateArticle(new ArticleBuilder().build());
		researcher3.allocateArticle(new ArticleBuilder().build());
		Researcher researcher4 = new ResearcherBuilder().id(3).build();

		Conference conference = new ConferenceBuilder().
				committeeMember(researcher1).
				committeeMember(researcher2).
				committeeMember(researcher3).
				committeeMember(researcher4).
				build();
		
		ArrayList<Researcher> expectedOrder = new ArrayList<Researcher>(Arrays.asList(researcher4, researcher1, researcher2, researcher3));
		assertEquals(conference.sortReviewers(new 
				ArrayList<Researcher>(Arrays.asList(researcher1, researcher2, researcher3, researcher4))), 
				expectedOrder);		
	}
	
	@Test
	public void testgetCandidateReviewers() {
		Researcher author = new ResearcherBuilder().build();
		
		author.setUniversity(new University("UFRGS"));
		Researcher revisor = new ResearcherBuilder().build();
		revisor.setUniversity(new University("Uniasselvi"));
		
		Article article1 = new ArticleBuilder()
			.id(1)
			 .author(author)
			  .build();
		
		assertTrue(revisor.isEligibleToReview(article1));
		assertFalse(author.isEligibleToReview(article1));
	}

	@Test
	public void testsetInitials() {
		Conference conference = new ConferenceBuilder().initials("foo").build();
		assertEquals(conference.getInitials(), "foo");		
	}

	@Test
	public void testgetLowestIDSubmittedArticle() {
		Conference conference = new ConferenceBuilder().build();
		Article article1 = new ArticleBuilder().id(1).build();
		Article article2 = new ArticleBuilder().id(2).build();
		Article article3 = new ArticleBuilder().id(3).build();
		conference.allocateArticle(article2, new ResearcherBuilder().build());
		conference.allocateArticle(article1, new ResearcherBuilder().build());
		conference.allocateArticle(article3, new ResearcherBuilder().build());

		assertEquals(conference.getLowestIDSubmittedArticle(), article1);		
	}

	@Test
	public void testgetAcceptedArticles() {
		Conference conference = new ConferenceBuilder().build();
		conference.allocateArticle(new ArticleBuilder().
				grade(new ResearcherBuilder().build(), 3.0f).build(),
				new ResearcherBuilder().build());

		conference.allocateArticle(new ArticleBuilder().
				grade(new ResearcherBuilder().build(), 0.0f).build(),
				new ResearcherBuilder().build());

		conference.allocateArticle(new ArticleBuilder().
				grade(new ResearcherBuilder().build(), -1.0f).build(),
				new ResearcherBuilder().build());

		assertEquals(conference.getAcceptedArticles().size(), 2);		
		assertEquals(conference.getRejectedArticles().size(), 1);		
	}
}
