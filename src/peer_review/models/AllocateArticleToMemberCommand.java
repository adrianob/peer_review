package peer_review.models;

public class AllocateArticleToMemberCommand extends Command {
	protected AllocateArticleToMemberCommand(UserInterface ui) {
		super(ui);
	}

	public void execute() {
		
	}

	@Override
	public String getName() {
		return "Aloca��o de artigos a membros do comit� de programa";
	}
}
