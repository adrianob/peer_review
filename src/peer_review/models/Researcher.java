package peer_review.models;

import java.util.ArrayList;

public class Researcher {
	private int id;
	private String name;
	private University affiliation;
	private ArrayList<ResearchTopic> researchTopics;
	public ArrayList<Article> allocatedArticles;

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
		// TODO: Implement
	}

	public Researcher(int id, String name, University affiliation, ArrayList<ResearchTopic> researchTopic,
			ArrayList<Article> allocatedArticles) {
		this.id = id;
		this.name = name;
		this.affiliation = affiliation;
		this.researchTopics = researchTopic;
		this.allocatedArticles = allocatedArticles;

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
