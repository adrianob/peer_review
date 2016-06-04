package peer_review.models;

public abstract class Command {
	public UserInterface ui;
	
	public void execute() {
		
	}
	
	public abstract String getName();
}
