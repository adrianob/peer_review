package peer_review.models;

import java.util.ArrayList;

public class AllocateArticleToMemberCommand extends Command {
	protected AllocateArticleToMemberCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		Conference conferenceSelected = ui.readConference();
		int numberOfReviewers = ui.readNumberOfRevewers(MIN_REVIEWERS, MAX_REVIEWERS);
		ui.showMessage("Iniciando alocação");
		while (conferenceSelected.hasUnreviewedArticles()) {
			Article lowest = conferenceSelected.getLowestIDSubmittedArticle();
			for (int i = 0; i < numberOfReviewers; i++) {
				ArrayList<Researcher> possibleReviewers = conferenceSelected.getCandidateReviewers(lowest);
				possibleReviewers = conferenceSelected.sortReviewers(possibleReviewers);
				conferenceSelected.allocateArticle(lowest, possibleReviewers.get(0));
				ui.showMessage("Artigo " + lowest.toStringSimple() + " alocado para o(a) pesquisador(a) " + possibleReviewers.get(0).toStringSimple());
			}
		}
		ui.showMessage("Fim da alocação");
	}

	@Override
	public String getName() {
		return "Alocação de artigos a membros do comitê de programa";
	}

	private int MIN_REVIEWERS = 2;
	private int MAX_REVIEWERS = 5;
}
