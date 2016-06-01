package peer_review.models;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class UserInterface {
	public Service service;
	
	public UserInterface(Service service) {
		this.service = service;
	}
	
	public void getCommand() {
		
	}
	
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	public String readString() {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		return s;
	}
	
	public int readInteger() {
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		return i;
	}
	
	public float readFloat() {
		Scanner scan = new Scanner(System.in);
		float f = scan.nextFloat();
		return f;
	}
	
	public void showUI() {
		
	}
	
	public void showArticlesList() {
		
	}
	
	public void showArticleReviewersList(Article article) {
		for (Researcher reviewer : article.getReviewers()) {
			System.out.println(reviewer.getName());
		}
	}
	
	public void showConferences() {
		
	}

	public void showArticlesWithGrades(ArrayList<Article> articlesList) {
		for (Article article : articlesList) {
			if (article.getGrades() != null)
				System.out.println(article.getTitle());
		}
	}
}
