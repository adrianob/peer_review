package peer_review.models;

import peer_review.data.*;
import peer_review.data.*;

public class Service {
	
	public Service(Database db) {
		this.db = db;
	}

	private Database db;

	public void allocArticlesToMembers(Conference conference, int numReviewers, UserInterface ui) {
		// TODO: Implement
	}

	public void rateArticle(Article article, Researcher reviewer, float rate, UserInterface ui) {
		// TODO: Implement
	}

	public void selectArticle(Conference conference, UserInterface ui) {
		// TODO: Implement
	}

	public Conference readConference() {
		// TODO: Implement
		return null;
	}
	
	public Researcher readResearcher() {
		// TODO: Implement
		return null;
	}
	
	public Article readArticle() {
		// TODO: Implement
		return null;
	}

}
