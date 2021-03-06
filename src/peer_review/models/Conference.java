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
		return articlesSubmitted.stream().filter(article -> !articlesAllocated().contains(article)).
				min(Comparator.comparingInt(Article::getID)).get();
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
		assert(articlesSubmitted.contains(lowestIDSubmittedArticle));
		assert(committeeMembers.contains(firstSortedResearcher));
		lowestIDSubmittedArticle.addReview(firstSortedResearcher, null);
		firstSortedResearcher.allocateArticle(lowestIDSubmittedArticle);
		return lowestIDSubmittedArticle;
	}

	private Stream<Article> getFilteredArticles(DoublePredicate predicate) {
	    return articlesAllocated().stream().filter(a -> predicate.test(a.getGradeAverage()));
	}

	public ArrayList<Article> articlesAllocated() {
	    return (ArrayList<Article>) articlesSubmitted.stream().
	    		filter(a -> a.numberOfReviewers() > 0).
	    		collect(Collectors.toList());
	}

	public ArrayList<Article> getAcceptedArticles() {
		Comparator<Article> byGrade = (a1, a2) -> Float.compare(
				a2.getGradeAverage(), a1.getGradeAverage());
	    return (ArrayList<Article>) getFilteredArticles((grade) -> grade >= 0).
	    		sorted(byGrade).collect(Collectors.toList());
	}

	public ArrayList<Article> getRejectedArticles() {
		Comparator<Article> byGrade = (a1, a2) -> Float.compare(
				a1.getGradeAverage(), a2.getGradeAverage());
	    return (ArrayList<Article>) getFilteredArticles((grade) -> grade < 0).
	    		sorted(byGrade).collect(Collectors.toList());
	}

	public int getSubmittedArticlesLenght() {
		return articlesSubmitted.size();
	}
	
	public boolean areArticlesAllocated() {
		return articlesSubmitted.size() == articlesAllocated().size();
	}
	
	public Researcher allocateToCommittee(Article article) {
		ArrayList<Researcher> possibleReviewers = getCandidateReviewers(article);
		possibleReviewers = sortReviewers(possibleReviewers);
		if (possibleReviewers.size() >= 1) {
			allocateArticle(article, possibleReviewers.get(0));
			return possibleReviewers.get(0);
		}
		return null;
	}

	public boolean hasUnreviewedArticles() {
		if(!areArticlesAllocated()) {
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
