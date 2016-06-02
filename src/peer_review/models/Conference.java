package peer_review.models;

import java.util.ArrayList;
import java.util.stream.*;

public class Conference {
	private String initials;
	private ArrayList<Article> articlesSubmitted;
	private ArrayList<Article> articlesAllocated;
	private ArrayList<Researcher> committeeMembers;
	private Researcher coordinator;

	public void setCoordinator(Researcher coordinator) {
		this.coordinator = coordinator;
	}

	public Conference(String initials, ArrayList<Article> articlesSubmitted, ArrayList<Article> articlesAllocated,
			ArrayList<Researcher> committeeMembers, Researcher coordinator) {
		this.initials = initials;
		this.articlesSubmitted = articlesSubmitted;
		this.articlesAllocated = articlesAllocated;
		this.committeeMembers = committeeMembers;
		this.coordinator = coordinator;
	}

	public Article getLowestIDSubmittedArticle() {
		int size = articlesSubmitted.size();
		int i;
		Article smallestIDArticle = articlesSubmitted.get(0);
		
		for (i=1;i<size;i++) {
			if (articlesSubmitted.get(i).getID() < smallestIDArticle.getID()) {
				smallestIDArticle = articlesSubmitted.get(i);
			}
		}
		return smallestIDArticle;
	}

	public ArrayList<Researcher> getCandidateReviewers(Article article) {
		ArrayList<Researcher> candidates = new ArrayList<Researcher>();
		
		for (Researcher possibleCandidate : committeeMembers) {
			boolean containsResearchTopic = possibleCandidate.getResearchTopics().contains(article.getResearchTopic());
			
			if (article.getAuthor() != possibleCandidate && article.getAuthorUniversity() != possibleCandidate.getUniversity() && containsResearchTopic && !(article.isResearcherAllocated(possibleCandidate))) {
				candidates.add(possibleCandidate);
			}
		}
		
		return candidates;
	}

	public ArrayList<Researcher> sortReviewers(ArrayList<Researcher> researchCandidates) {
		ArrayList<Researcher> sortedResearchers = new ArrayList<Researcher>();
		Researcher researcherWithLeastArticles = researchCandidates.get(0);
		
		while (researchCandidates.size() > 0) {
			for (Researcher researchCandidate : researchCandidates) {
				int articlesAmount = researchCandidate.getAlocatedArticles().size();
				
				if (articlesAmount < researcherWithLeastArticles.getAlocatedArticles().size()) {
					researcherWithLeastArticles = researchCandidate;
					
				} else if (articlesAmount == researcherWithLeastArticles.getAlocatedArticles().size()) {
					if (researchCandidate.getID() < researcherWithLeastArticles.getID()) {
						researcherWithLeastArticles = researchCandidate;
					}
				}
			}
			
			sortedResearchers.add(researcherWithLeastArticles);
			researchCandidates.remove(researcherWithLeastArticles);
		}
		
		return sortedResearchers;
	}

	public Article allocateArticle(Article lowestIDSubmittedArticle, Researcher firstSortedResearcher) {
		lowestIDSubmittedArticle.allocateReviewer(firstSortedResearcher);
		firstSortedResearcher.allocateArticle(lowestIDSubmittedArticle);
		articlesSubmitted.add(lowestIDSubmittedArticle);
		return lowestIDSubmittedArticle;
	}

	public ArrayList<Article> getAcceptedArticles() {
	    ArrayList<Article> acceptedArticles = (ArrayList<Article>) articlesSubmitted.stream().
	    		filter(a -> a.getGradeAverage() >= 0).
	    		collect(Collectors.toList());

		return acceptedArticles;
	}

	public String getInitials() {
		return this.initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public ArrayList<Article> getRejectedArticles() {
	    ArrayList<Article> rejectedArticles = (ArrayList<Article>) articlesSubmitted.stream().
	    		filter(a -> a.getGradeAverage() < 0).
	    		collect(Collectors.toList());
		return rejectedArticles;
	}

	public Researcher getCoordinator() {
		return coordinator;
	}

	public boolean hasUnreviewedArticles() {
		for (Article submittedArticle : articlesSubmitted) {
			if (!articlesAllocated.contains(submittedArticle)) {
				return false;
			}
		}
		return true;
	}

	public String toStringSimple() {
		return getInitials();
	}

	@Override
	public String toString() {
		String result = toStringSimple() + "\n";
		result += "Articles submitted:\n";
		for (Article article : articlesSubmitted) {
			result += article.toStringSimple() + "\n";
		}

		result += "Committe members:\n";
		for (Researcher member : committeeMembers) {
			result += member.toStringSimple() + "\n";
		}

		result += "Coordinator:\n" + coordinator.toStringSimple() + "\n";
		return result;
	}
}
