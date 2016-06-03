package peer_review.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import peer_review.models.Conference;
import peer_review.builders.*;

public class ConferenceTest {

	@Test
	public void testsetInitials() {
		Conference conference = new ConferenceBuilder().initials("foo").build();
		assertEquals(conference.getInitials(), "foo");		
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
