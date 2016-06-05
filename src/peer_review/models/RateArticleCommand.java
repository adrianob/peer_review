package peer_review.models;

public class RateArticleCommand extends Command {
	protected RateArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		ui.showArticlesList();
		System.out.println("Selecione o id do artigo");
		
		int articleID = ui.readInteger();
		Article chosenArticle = ui.service.readArticle(articleID);

		ui.showArticleReviewersList(chosenArticle);

		attributeGrade(chosenArticle);
	}

	@Override
	public String getName() {
		return "Atribuição de notas a artigos";
	}
	
	private void attributeGrade(Article chosenArticle){
		System.out.println("Selecione o id do revisor");
		int reviewerID = ui.readInteger();
		Researcher researcher = ui.service.readResearcher(reviewerID);

		System.out.println("Selecione uma nota entre -3 e 3");
		float grade = ui.readFloat();
		
		ui.service.rateArticle(chosenArticle, researcher, grade);
	}
}
