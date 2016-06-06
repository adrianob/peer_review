package peer_review.builders;

import peer_review.models.ResearchTopic;
import peer_review.models.Researcher;
import peer_review.models.University;

import java.util.ArrayList;
import java.util.Arrays;


public class ResearcherBuilder {

	private Researcher researcher;
	 
    public ResearcherBuilder() {
        researcher = new Researcher(1,
        		"researcher name",
        		new University("UFRGS"),
				new ArrayList<>(Arrays.asList(new ResearchTopic("topic 1"), new ResearchTopic("topic 2")))
        		);
    }
 
    public ResearcherBuilder name(String name) {
        researcher.setName(name);
        return this;
    }

    public ResearcherBuilder id(int id) {
        researcher.setId(id);
        return this;
    }

    public Researcher build() {
        return researcher;
    }
}
