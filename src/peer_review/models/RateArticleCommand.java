package peer_review.models;

public class RateArticleCommand extends Command {
	protected RateArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		ui.showArticlesList();
		Article chosenArticle = ui.readArticle();
		ui.showArticleReviewersList(chosenArticle);
		attributeGrade(chosenArticle);
	}

	@Override
	public String getName() {
		return "Atribuição de notas a artigos";
	}

	private void attributeGrade(Article chosenArticle) {
		Researcher researcher = ui.readReviewer();
		float grade = ui.readGrade(-3, 3);
		ui.service.rateArticle(chosenArticle, researcher, grade);
	}
}
