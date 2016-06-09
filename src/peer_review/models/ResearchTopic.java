package peer_review.models;

public class ResearchTopic {
	private String name;

	public ResearchTopic(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public boolean equals(Object o) {
		return ((ResearchTopic) o).getName() == this.getName();
	}
}