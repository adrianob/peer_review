package peer_review.models;

public class RateArticleCommand extends Command {
	protected RateArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		ui.showArticlesList();
		ui.showMessage("Selecione o id do artigo");
		int articleID = ui.readInteger();
		Article chosenArticle = ui.service.readArticle(articleID);
		
		ui.showArticleReviewersList(chosenArticle);
		attributeGrade(chosenArticle);
	}

	@Override
	public String getName() {
		return "Atribuição de notas a artigos";
	}

	private void attributeGrade(Article chosenArticle) {
		ui.showMessage("Selecione o id do revisor");
		int reviewerID = ui.readInteger();
		Researcher researcher = ui.service.readResearcher(reviewerID);

		ui.showMessage("Selecione uma nota entre -3.0 e 3.0");
		float grade = ui.readFloat(-3, 3);

		ui.service.rateArticle(chosenArticle, researcher, grade);
	}
}
