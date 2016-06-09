package peer_review.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import peer_review.data.*;
import peer_review.ui.UserInterface;

public class Service {
	
	public Service(Database db) {
		this.db = db;
	}

	private Database db;

	public Researcher allocateToCommittee(Article article, Conference conference) {
		return conference.allocateToCommittee(article);
	}

	public Article getLowestIDSubmittedArticle(Conference conference) {
		return conference.getLowestIDSubmittedArticle();
	}

	public boolean areArticlesAllocated(Conference conference) {
		return conference.areArticlesAllocated();
	}

	public void rateArticle(Article article, Researcher reviewer, float rate) {
		article.rate(reviewer, Optional.ofNullable(rate));
	}

	public Conference readConference(String initials) {
		return db.getConferenceByInitials(initials);
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

	public ArrayList<Article> getRejectedArticles(Conference conference) {
		return conference.getRejectedArticles();
	}

	public ArrayList<Article> getAcceptedArticles(Conference conference) {
		return conference.getAcceptedArticles();
	}

	public Collection<Conference> getConferences() {
		return db.getConferences();
	}
	
	public boolean hasUnreviewdArticles(Conference conference) {
		return conference.hasUnreviewedArticles();
	}
}
