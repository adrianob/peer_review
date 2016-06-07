package peer_review.models;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class UserInterface {
	public Service service;
	public ArrayList<Command> commands = new ArrayList<Command>();

	public UserInterface(Service service) {
		this.service = service;
		commands.add(new RateArticleCommand(this));
		commands.add(new SelectArticleCommand(this));
		commands.add(new AllocateArticleToMemberCommand(this));
	}

	public void getCommand() {
		int selection = readInteger(0, commands.size() - 1);
		commands.get(selection).execute();
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public String readString() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			try {
				String s = scan.nextLine();
				// scan.close();
				return s;
			} catch (Exception e) {
				showMessage("Digite uma string!");
			}
		}
	}

	//@TODO merge with selectConference
	public Conference readConference() {
		showMessage("Digite o nome de uma das conferencias");
		for (Conference conference : service.getConferences()) {
			showMessage(conference.toStringSimple());
		}

		while (true) {
			String selected = readString();
			for (Conference conference : service.getConferences()) {
				if (selected.equals(conference.getInitials())) {
					return conference;
				}
			}
			showMessage("Conferencia inválida, tente de novo");
		}
	}
	
	public int readNumberOfRevewers(int min, int max) {
		showMessage("Digite a quantidade de revisores, entre " + min + " e " + max);
		return readInteger(min, max);
	}

	public int readInteger() {
		return readInteger(0, 0, false);
	}

	public int readInteger(int min, int max) {
		return readInteger(min, max, true);
	}

	private int readInteger(int min, int max, boolean checkLimits) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			try {
				int i = scan.nextInt();
				if (checkLimits && (i < min || i > max)) {
					showMessage("Digite um número entre " + min + " e " + max);
				} else {
					// scan.close();
					return i;
				}
			} catch (Exception e) {
				showMessage("Digite um número inteiro!");
			}
		}
	}

	public float readFloat(float min, float max) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			try {
				float f = scan.nextFloat();
				if (f < min || f > max) {
					showMessage("Digite um número entre " + min + " e " + max);
				} else {
					// scan.close();
					return f;
				}
			} catch (Exception e) {
				showMessage("Digite um número em ponto flutuante!");
			}
		}
	}

	public void showUI() {
		showMessage("Opções:");
		for (int i = 0; i < commands.size(); i++) {
			showMessage(i + ":" + commands.get(i).getName());
		}
	}
	
	public Conference selectConference() {
		showMessage("Selecione a conferência");
		String initials = readString();
		return service.readConference(initials.trim());
	}

	public void showArticlesList() {
		for (Article article : service.getArticles()) {
			System.out.println(article.toStringSimple());
		}
	}

	public void showArticleReviewersList(Article article) {
		for (Researcher reviewer : article.getReviewers()) {
			System.out.println(reviewer.toStringSimple());
		}
	}

	public void showConferences() {
		for (Conference conference : service.getConferences()) {
			System.out.println(conference.toStringSimple());
		}
	}

	public void showArticlesWithGrades(ArrayList<Article> articlesList) {
		for (Article article : articlesList) {
			if (article.getGrades().size() > 0)
				System.out.println(article.getTitle() + " " + article.getGradeAverage());
		}
	}
}
