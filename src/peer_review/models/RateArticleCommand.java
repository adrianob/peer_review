package peer_review.models;

public class RateArticleCommand extends Command {
	protected RateArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		ui.showArticlesList();
	}

	@Override
	public String getName() {
		return "Atribuição de notas a artigos";
	}
}
