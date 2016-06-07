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
		//ui.showMessage(conferenceSelected.getSubmittedArticlesLenght()+"");
		while (conferenceSelected.getSubmittedArticlesLenght() > 0) {
			Article lowest = conferenceSelected.getLowestIDSubmittedArticle();
			while (lowest.getReviewers().size() < numberOfReviewers) {
				ArrayList<Researcher> a = conferenceSelected.getCandidateReviewers(lowest);
				a = conferenceSelected.sortReviewers(a);
				conferenceSelected.allocateArticle(lowest, a.get(0));
				ui.showMessage("Artigo " + lowest.toStringSimple() + " alocado para pesquisador" + a.get(0));
			}
		}
		ui.showMessage("Fim da alocação");
	}

	@Override
	public String getName() {
		return "Alocaï¿½ï¿½o de artigos a membros do comitï¿½ de programa";
	}

	private int MIN_REVIEWERS = 2;
	private int MAX_REVIEWERS = 5;
}
