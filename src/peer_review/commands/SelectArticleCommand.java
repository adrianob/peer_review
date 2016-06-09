package peer_review.commands;

import java.util.stream.Collectors;

import peer_review.models.Conference;
import peer_review.ui.UserInterface;

public class SelectArticleCommand extends Command {
	public SelectArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		Conference conference = ui.readConference(ui.service.getConferences().stream().collect(Collectors.toList()));
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
