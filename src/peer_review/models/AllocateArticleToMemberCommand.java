package peer_review.models;

import java.util.ArrayList;

public class AllocateArticleToMemberCommand extends Command {
	protected AllocateArticleToMemberCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		Conference conferenceSelected = ui.readConference();
		int numberOfReviewers = ui.readNumberOfReviewers(MIN_REVIEWERS, MAX_REVIEWERS);
		ui.showMessage("Iniciando alocação");
		while (!conferenceSelected.areArticlesAllocated()) {
			Article lowest = conferenceSelected.getLowestIDSubmittedArticle();
			for (int i = 0; i < numberOfReviewers; i++) {
				Researcher allocated = conferenceSelected.allocateToCommittee(lowest);
				if (allocated != null) {
					ui.showMessage("Artigo " + lowest.toStringSimple() + " alocado para o(a) pesquisador(a) " + allocated.toStringSimple());
				}
				else
				{
					ui.showMessage("Não foi encontrado outro(a) membro do comite que possa realizar a revisão desse artigo");
					break;
				}
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
