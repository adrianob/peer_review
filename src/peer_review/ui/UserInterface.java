package peer_review.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import peer_review.commands.*;
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
		showResearchers(reviewers);
		while (true) {
			int reviewerID = readInteger();
			ArrayList<Researcher> results = (ArrayList<Researcher>) reviewers.stream()
					.filter(item -> item.getID() == reviewerID).collect(Collectors.toList());
			if (results.size() == 0) {
				showMessage("ID inválido, tente de novo");
			} else {
				return results.get(0);
			}
		}
	}

	public Article readArticle(List<Article> articles) {
		showMessage("Selecione o id do artigo");
		showArticles(articles);
		while (true) {
			int articleID = readInteger();
			ArrayList<Article> results = (ArrayList<Article>) articles.stream()
					.filter(item -> item.getID() == articleID).collect(Collectors.toList());
			if (results.size() == 0) {
				showMessage("ID inválido, tente de novo");
			} else {
				return results.get(0);
			}
		}
	}

	public Conference readConference(List<Conference> conferences) {
		showMessage("Digite o nome da conferência");
		showConferences(conferences);
		while (true) {
			String conferenceInitials = readString();
			ArrayList<Conference> results = (ArrayList<Conference>) conferences.stream()
					.filter(item -> item.getInitials().equals(conferenceInitials)).collect(Collectors.toList());
			if (results.size() == 0) {
				showMessage("Conferencia inválida, tente de novo");
			} else {
				return results.get(0);
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

	public void showResearchers(List<Researcher> researchers) {
		for (Researcher researcher : researchers) {
			showMessage(researcher.toStringSimple());
		}
	}

	public void showArticles(List<Article> articles) {
		for (Article article : articles) {
			showMessage(article.toStringSimple());
		}
	}

	public void showConferences(List<Conference> conferences) {
		for (Conference conference : conferences) {
			showMessage(conference.toStringSimple());
		}
	}

	public void showArticlesWithGrades(List<Article> articlesList) {
		for (Article article : articlesList) {
			if (article.getGrades().size() > 0)
				showMessage(article.getTitle() + " " + article.getGradeAverage());
		}
	}
}
