package peer_review.models;

public class SelectArticleCommand extends Command {
	protected SelectArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		
	}

	@Override
	public String getName() {
		return "Sele��o de artigos";
	}
}
