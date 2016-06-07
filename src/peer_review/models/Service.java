package peer_review.models;

import java.util.Collection;

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

	public void rateArticle(Article article, Researcher reviewer, float rate) {
		article.setGrade(reviewer, rate);
	}

	public void selectArticle(Conference conference, UserInterface ui) {
		// TODO: Implement
	}

	public Conference readConference() {
		// TODO: Implement
		return null;
	}
	
	public Researcher readResearcher(int id) {
		return db.getResearcherById(id);
	}
	
	public Article readArticle(int id) {
		return db.getArticleById(id);
	}

	public Collection<Article> getArticles() {
		return db.getArticles();
	}
	
	public Collection<Conference> getConferences() {
		return db.getConferences();
	}
}
