package peer_review.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import peer_review.commands.AllocateArticleToMemberCommand;
import peer_review.commands.Command;
import peer_review.commands.RateArticleCommand;
import peer_review.commands.SelectArticleCommand;
import peer_review.models.Article;
import peer_review.models.Conference;
import peer_review.models.Researcher;
import peer_review.models.Service;

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
				return s.trim();
			} catch (Exception e) {
				showMessage("Digite uma string!");
			}
		}
	}

	public Researcher readReviewer(List<Researcher> reviewers) {
		showMessage("Selecione o id do revisor");
		for (Researcher reviewer : reviewers) {
			showMessage(reviewer.toStringSimple());
		}
		while (true) {
			int reviewerID = readInteger();
			boolean isIDvalid = reviewers.stream().anyMatch(a -> a.getID() == reviewerID);
			if (!isIDvalid) {
				showMessage("ID inválido, tente de novo");
			} else {
				return service.readResearcher(reviewerID);
			}
		}
	}

	public Article readArticle() {
		showMessage("Selecione o id do artigo");
		showArticlesList();
		while (true) {
			int articleID = readInteger();
			Article article = service.readArticle(articleID);
			if (article == null) {
				showMessage("ID inválido, tente de novo");
			} else {
				return article;
			}
		}

	}

	public Conference readConference() {
		showMessage("Digite o nome da conferência");
		ArrayList<Conference> conferences = (ArrayList<Conference>) service.getConferences().stream().filter(a -> !a.areArticlesAllocated()).collect(Collectors.toList());
		for (Conference conference : conferences) {
			showMessage(conference.toStringSimple());
		}

		while (true) {
			String conferenceInitials = readString();
			boolean contains = conferences.stream().anyMatch(a -> a.getInitials().equals(conferenceInitials));
			if (contains) {
				return service.readConference(conferenceInitials);
			}
			else {
				showMessage("Conferencia inválida, tente de novo");
			}
		}
	}

	public float readGrade(float min, float max) {
		showMessage("Digite a nota, entre " + min + " e " + max);
		return readFloat(min, max);
	}

	public int readNumberOfReviewers(int min, int max) {
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
