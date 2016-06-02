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

}
