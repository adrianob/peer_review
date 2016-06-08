package peer_review.builders;
import java.util.ArrayList;

import peer_review.models.Conference;
import peer_review.models.Researcher;

public class ConferenceBuilder {
	private Conference conference;
	 
    public ConferenceBuilder() {
        conference = new Conference("initials",
						new ArrayList<Researcher>()
        		);
    }
 
    public ConferenceBuilder initials(String initials) {
        conference.setInitials(initials);
        return this;
    }

    public ConferenceBuilder committeeMember(Researcher researcher) {
        conference.addCommitteeMember(researcher);
        return this;
    }

    public Conference build() {
        return conference;
    }

}
