package peer_review.models;

import java.util.ArrayList;

public class AllocateArticleToMemberCommand extends Command {
	protected AllocateArticleToMemberCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		Conference conferenceSelected = ui.readConference();
		int numberOfReviewers = ui.readNumberOfRevewers(MIN_REVIEWERS, MAX_REVIEWERS);

		ui.showMessage("Iniciando aloca��o");
		while (conferenceSelected.getSubmittedArticlesLenght() > 0) {
			Article lowest = conferenceSelected.getLowestIDSubmittedArticle();
			while (lowest.getReviewers().size() < numberOfReviewers) {
				ArrayList<Researcher> possibleReviewers = conferenceSelected.getCandidateReviewers(lowest);
				possibleReviewers = conferenceSelected.sortReviewers(possibleReviewers);
				conferenceSelected.allocateArticle(lowest, possibleReviewers.get(0));
				numberOfReviewers = numberOfReviewers - 1;
				ui.showMessage("Artigo " + lowest.toStringSimple() + " alocado para o(a) pesquisador(a) " + possibleReviewers.get(0).toStringSimple());
			}
		}
		ui.showMessage("Fim da aloca��o");
	}

	@Override
	public String getName() {
		return "Aloca��o de artigos a membros do comit� de programa";
	}

	private int MIN_REVIEWERS = 2;
	private int MAX_REVIEWERS = 5;
}
