package peer_review.models;

public class RateArticleCommand extends Command {
	protected RateArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		ui.showArticlesList();
		Article chosenArticle = ui.readArticle();
		attributeGrade(chosenArticle);
	}

	@Override
	public String getName() {
		return "Atribuição de notas a artigos";
	}

	private void attributeGrade(Article chosenArticle) {
		Researcher researcher = ui.readReviewer(chosenArticle.getReviewers());
		float grade = ui.readGrade(Article.MIN_GRADE, Article.MAX_GRADE);
		ui.service.rateArticle(chosenArticle, researcher, grade);
	}
}
