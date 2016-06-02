package peer_review.builders;
import java.util.ArrayList;
import java.util.Arrays;

import peer_review.models.Conference;
import peer_review.models.Researcher;
import peer_review.models.Article;

public class ConferenceBuilder {
	private Conference conference;
	 
    public ConferenceBuilder() {
        conference = new Conference("initials",
						new ArrayList<Article>(Arrays.asList()),
						new ArrayList<Article>(Arrays.asList()),
						new ArrayList<Researcher>(Arrays.asList(new ResearcherBuilder().name("res1").build(), new ResearcherBuilder().name("res2").build())),
						new ResearcherBuilder().name("coordinator").build()
        		);
    }
 
    public ConferenceBuilder coordinator(Researcher coordinator) {
        conference.setCoordinator(coordinator);
        return this;
    }
 
    public ConferenceBuilder initials(String initials) {
        conference.setInitials(initials);
        return this;
    }

    public Conference build() {
        return conference;
    }

}
