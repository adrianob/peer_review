package peer_review.commands;

import peer_review.ui.UserInterface;

public abstract class Command {
	public UserInterface ui;
	
	protected Command(UserInterface ui){
		this.ui = ui;
	}

	public void execute() {
		
	}
	
	public abstract String getName();
}
