package peer_review.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import peer_review.models.Article;
import peer_review.models.Conference;
import peer_review.models.Researcher;
import peer_review.ui.UserInterface;

public class AllocateArticleToMemberCommand extends Command {
	public AllocateArticleToMemberCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		ArrayList<Conference> conferences = (ArrayList<Conference>) ui.service.getConferences().stream()
				.filter(a -> !a.areArticlesAllocated()).collect(Collectors.toList());
		if (conferences.size() == 0) {
			ui.showMessage("Todas as conferências já tiveram os artigos alocados");
			return;
		}
		Conference conferenceSelected = ui.readConference(conferences);
		int numberOfReviewers = ui.readNumberOfReviewers(MIN_REVIEWERS, MAX_REVIEWERS);
		ui.showMessage("Iniciando alocação");
		while (!ui.service.areArticlesAllocated(conferenceSelected)) {
			Article lowest = ui.service.getLowestIDSubmittedArticle(conferenceSelected);
			for (int i = 0; i < numberOfReviewers; i++) {
				Researcher allocated = ui.service.allocateToCommittee(lowest, conferenceSelected);
				if (allocated != null) {
					ui.showMessage("Artigo " + lowest.toStringSimple() + " alocado para o(a) pesquisador(a) "
							+ allocated.toStringSimple());
				} else {
					ui.showMessage("Falha na alocação");
					return;
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
