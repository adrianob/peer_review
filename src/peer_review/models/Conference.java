package peer_review.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.DoublePredicate;
import java.util.stream.*;

public class Conference {
	private String initials;
	private ArrayList<Article> articlesSubmitted;
	private ArrayList<Researcher> committeeMembers;

	public Conference(String initials, ArrayList<Researcher> committeeMembers) {
		this.initials = initials;
		this.articlesSubmitted = new ArrayList<Article>();
		this.committeeMembers = committeeMembers;
	}

	public void addArticlesSubmitted(Article articleSubmitted) {
		this.articlesSubmitted.add(articleSubmitted);
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public void addCommitteeMember(Researcher member) {
		committeeMembers.add(member);
	}

	public String getInitials() {
		return this.initials;
	}

	public Article getLowestIDSubmittedArticle() {
		return articlesSubmitted.stream().min(Comparator.comparingInt(Article::getID)).get();
	}

	public ArrayList<Researcher> getCandidateReviewers(Article article) {
	    return (ArrayList<Researcher>) committeeMembers.stream().
	    		filter(candidate -> candidate.isEligibleToReview(article)).
	    		collect(Collectors.toList());
	}

	public ArrayList<Researcher> sortReviewers(ArrayList<Researcher> researchCandidates) {
		Comparator<Researcher> byAllocatedArticles = (r1, r2) -> Integer.compare(
				r1.getAlocatedArticles().size(), r2.getAlocatedArticles().size());
		Comparator<Researcher> byID = (r1, r2) -> Integer.compare(r1.getID(), r2.getID());

	    return (ArrayList<Researcher>) researchCandidates.stream().
	    		sorted(byAllocatedArticles.thenComparing(byID)).collect(Collectors.toList());
	}

	public Article allocateArticle(Article lowestIDSubmittedArticle, Researcher firstSortedResearcher) {
		//@TODO should create review instead
		//lowestIDSubmittedArticle.allocateReviewer(firstSortedResearcher);
		firstSortedResearcher.allocateArticle(lowestIDSubmittedArticle);
		assert(articlesSubmitted.contains(lowestIDSubmittedArticle));
		//articlesSubmitted.remove(lowestIDSubmittedArticle);
		//articlesAllocated().add(lowestIDSubmittedArticle);
		return lowestIDSubmittedArticle;
	}

	private ArrayList<Article> getFilteredArticles(DoublePredicate predicate) {
	    return (ArrayList<Article>) articlesAllocated().stream().
	    		filter(a -> predicate.test(a.getGradeAverage())).
	    		collect(Collectors.toList());
	}

	public ArrayList<Article> articlesAllocated() {
	    return (ArrayList<Article>) articlesSubmitted.stream().
	    		filter(a -> a.numberOfReviewers() > 0).
	    		collect(Collectors.toList());
	}

	//@TODO order by average grade
	public ArrayList<Article> getAcceptedArticles() {
	    return getFilteredArticles((grade) -> grade >= 0);
	}

	public ArrayList<Article> getRejectedArticles() {
	    return getFilteredArticles((grade) -> grade < 0);
	}

	public int getSubmittedArticlesLenght() {
		return articlesSubmitted.size();
	}

	public boolean hasUnreviewedArticles() {
		if(articlesSubmitted.size() != articlesAllocated().size()) {
			return true;
		}
		for (Article allocatedArticle : articlesAllocated()) {
			if (allocatedArticle.getGrades().stream().anyMatch(grade -> !grade.getGrade().isPresent())) {
				return true;
			}
		}

		return false;
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

		return result;
	}
}
