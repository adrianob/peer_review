package peer_review.models;

public class SelectArticleCommand extends Command {
	protected SelectArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		Conference conference = ui.readConference();
		if (ui.service.hasUnreviewdArticles(conference)) {
			ui.showMessage("Notas pendentes");
			return;
		}
		else {
			ui.showMessage("Artigos aceitos:");
			ui.showArticlesWithGrades(ui.service.getAcceptedArticles(conference));
			ui.showMessage("Artigos rejeitados:");
			ui.showArticlesWithGrades(ui.service.getRejectedArticles(conference));
		}

	}

	@Override
	public String getName() {
		return "Seleção de artigos";
	}
}
