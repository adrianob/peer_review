package peer_review.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.DoublePredicate;
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

	public Conference(String initials, ArrayList<Researcher> committeeMembers, Researcher coordinator) {
		this.initials = initials;
		this.articlesSubmitted = new ArrayList<Article>();
		this.articlesAllocated = new ArrayList<Article>();
		this.committeeMembers = committeeMembers;
		this.coordinator = coordinator;
	}

	public void addCommitteeMember(Researcher member) {
		committeeMembers.add(member);
	}

	public Article getLowestIDSubmittedArticle() {
		return articlesSubmitted.stream().min(Comparator.comparingInt(Article::getID)).get();
	}

	public ArrayList<Researcher> getCandidateReviewers(Article article) {
	    return (ArrayList<Researcher>) committeeMembers.stream().
	    		filter(candidate -> article.getAuthor() == candidate || 
						article.getAuthorUniversity() == candidate.getUniversity() ||
						!candidate.getResearchTopics().contains(article.getResearchTopic()) ||
						(article.isResearcherAllocated(candidate))
	    		).collect(Collectors.toList());
	}

	public ArrayList<Researcher> sortReviewers(ArrayList<Researcher> researchCandidates) {
		Comparator<Researcher> byAllocatedArticles = (r1, r2) -> Integer.compare(r1.
				getAlocatedArticles().size(), r2.getAlocatedArticles().size());
		Comparator<Researcher> byID = (r1, r2) -> Integer.compare(r1.getID(), r2.getID());

	    return (ArrayList<Researcher>) researchCandidates.stream().
	    		sorted(byAllocatedArticles.thenComparing(byID)).collect(Collectors.toList());
	}

	public Article allocateArticle(Article lowestIDSubmittedArticle, Researcher firstSortedResearcher) {
		lowestIDSubmittedArticle.allocateReviewer(firstSortedResearcher);
		firstSortedResearcher.allocateArticle(lowestIDSubmittedArticle);
		assert(articlesSubmitted.contains(lowestIDSubmittedArticle));
		articlesSubmitted.remove(lowestIDSubmittedArticle);
		articlesAllocated.add(lowestIDSubmittedArticle);
		return lowestIDSubmittedArticle;
	}

	public String getInitials() {
		return this.initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	private ArrayList<Article> getFilteredArticles(DoublePredicate predicate) {
	    return (ArrayList<Article>) articlesSubmitted.stream().
	    		filter(a -> predicate.test(a.getGradeAverage())).
	    		collect(Collectors.toList());
	}

	public ArrayList<Article> getAcceptedArticles() {
	    return getFilteredArticles((grade) -> grade >= 0);
	}

	public ArrayList<Article> getRejectedArticles() {
	    return getFilteredArticles((grade) -> grade < 0);
	}

	public Researcher getCoordinator() {
		return coordinator;
	}
	
	public int getSubmittedArticlesLenght() {
		return articlesSubmitted.size();
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
