package peer_review.models;

public class University {
	private String name;

	public University(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}
}