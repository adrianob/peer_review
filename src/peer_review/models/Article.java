package peer_review.models;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Article {
	public static final float MAX_GRADE = 3.0f;
	public static final float MIN_GRADE = -3.0f;
	private int id;
	private String title;
	private Researcher author;
	private Conference conference;
	private ResearchTopic researchTopic;
	private List<Review> reviews;

	public Article(int id, String title, Researcher author, Conference conference,
			ResearchTopic researchTopic, List<Review> reviews) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.conference = conference;
		this.researchTopic = researchTopic;
		this.reviews = reviews;
		conference.addArticlesSubmitted(this);
	}

	public List<Researcher> reviewers() {
		return this.reviews.stream().map(Review::getReviewer).collect(Collectors.toList());
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(Researcher author) {
		this.author = author;
	}

	public void rate(Researcher researcher, Optional<Float> score) {
		this.reviews.stream().filter(review -> review.getReviewer() == researcher).
		forEach(review -> review.setGrade(score));
	}

	public void addReview(Researcher researcher, Optional<Float> grade) {
		reviews.add(new Review(researcher, grade));
	}

	public University getAuthorUniversity() {
		return author.getUniversity();
	}
	
	public int getID() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Researcher getAuthor() {
		return author;
	}

	public ResearchTopic getResearchTopic() {
		return researchTopic;
	}
	
	public List<Review> getGrades() {
		return reviews;
	}

	public List<Researcher> getReviewers() {
		return reviewers();
	}

	public Boolean isResearcherAllocated(Researcher researcher) {
		return reviewers().contains(researcher);
	}

	public int numberOfReviewers() {
		return reviewers().size();
	}
	
	public float getGradeAverage() {
		return (float) reviews.stream().
				mapToDouble((grade) -> grade.getGrade().get()).average().getAsDouble();
	}

	public String toStringSimple() {
		return "ID:" + getID() + " Article:" + getTitle();
	}

	@Override
	public String toString() {
		String result = toStringSimple() + "\n";
		result += "Author:\n" + author.toStringSimple() + "\n";
		result += "Reviewers:\n";
		for (Researcher reviewer : reviewers()) {
			result += reviewer.toStringSimple() + "\n";
		}

		result += "Conference:\n" + conference.toStringSimple() + "\n";
		result += "Research topic:\n" + researchTopic.toString() + "\n";
		return result;
	}

}
