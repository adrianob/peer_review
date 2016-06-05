package peer_review.models;

public abstract class Command {
	public UserInterface ui;
	
	protected Command(UserInterface ui){
		this.ui = ui;
	}

	public void execute() {
		
	}
	
	public abstract String getName();
}
