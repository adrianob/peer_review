package peer_review.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import peer_review.models.Article;
import peer_review.models.Researcher;
import peer_review.ui.UserInterface;

public class RateArticleCommand extends Command {
	public RateArticleCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		ArrayList<Article> hasReviewers = (ArrayList<Article>) ui.service.getArticles().stream()
				.filter(a -> a.numberOfReviewers() > 0).collect(Collectors.toList());
		Article chosenArticle = ui.readArticle(hasReviewers);
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
