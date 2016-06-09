package peer_review.models;
import peer_review.data.*;
import peer_review.ui.UserInterface;
public class PeerReview {
	public static UserInterface ui;
	public static Database db;
	public static Service service;
	
	public static void main(String [] args) {
		db = new Database(true);
		service = new Service(db);
		ui = new UserInterface(service);
		
		while(true) {
			ui.showUI();
			ui.getCommand();
			
		}
	}
}
