package peer_review.models;

import java.util.Optional;

public class Grade {
	private Researcher reviewer;
	private Optional<Float> grade;
	
	public Grade(Researcher reviewer, Optional<Float> grade) {
		this.setReviewer(reviewer);
		this.setGrade(grade);
	}

	public Researcher getReviewer() {
		return reviewer;
	}

	private void setReviewer(Researcher reviewer) {
		this.reviewer = reviewer;
	}

	public Optional<Float> getGrade() {
		return grade;
	}

	public void setGrade(Optional<Float> grade) {
		this.grade = grade;
	}
	
}
