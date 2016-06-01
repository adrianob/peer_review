package peer_review.models;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
	public Service service;
	
	public UserInterface(Service service) {
		
	}
	
	public void getCommand() {
		
	}
	
	public void showMessage(String message) {
		
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
		
	}
	
	public void showConferences() {
		
	}

	public void showArticlesWithGrades(ArrayList<Article> articlesList) {
	
	}
}
