package peer_review.models;

import java.util.ArrayList;

public class Researcher {
	private int id;
	private String name;
	private University affiliation;
	private ArrayList<ResearchTopic> researchTopics;
	public ArrayList<Article> allocatedArticles;

	public Researcher(int id, String name, University affiliation, ArrayList<ResearchTopic> researchTopics) {
		this.id = id;
		this.name = name;
		this.affiliation = affiliation;
		this.researchTopics = researchTopics;
		this.allocatedArticles = new ArrayList<Article>();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setUniversity(University affiliation) {
		this.affiliation = affiliation;
	}

	public ArrayList<ResearchTopic> getResearchTopics() {
		return researchTopics;
	}
	
	public ArrayList<Article> getAlocatedArticles() {
		return allocatedArticles;
	}

	public University getUniversity() {
		return affiliation;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public void allocateArticle(Article article) {
		allocatedArticles.add(article);
	}
	
	public boolean isEligibleToReview(Article article) {
		return !(this == article.getAuthor() || 
				article.getAuthorUniversity() == this.getUniversity() ||
				!this.getResearchTopics().contains(article.getResearchTopic()) ||
				article.isResearcherAllocated(this));
	}

	public String toStringSimple() {
		return "ID:" + getID() + " Name:" + getName();
	}

	@Override
	public String toString() {
		String result = toStringSimple() + "\n";
		result += "Affiliation:" + affiliation.toString() + "\n";
		result += "Research topics:\n";
		for (ResearchTopic topic : researchTopics) {
			result += topic.toString() + "\n";
		}

		result += "Allocated articles:\n";
		for (Article article : allocatedArticles) {
			result += article.toStringSimple() + "\n";
		}

		return result;
	}
}
