package peer_review.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import peer_review.models.Article;
import peer_review.models.Conference;
import peer_review.models.ResearchTopic;
import peer_review.models.Researcher;
import peer_review.models.University;

public class ArticleTest {
	Article article;
	String articleTitle;
	Conference articleConference;
	int articleID;
	ResearchTopic articleResearchTopic;
	ArrayList<Researcher> articleReviewers;

	Researcher author;
	University authorUniversity;
	ArrayList<ResearchTopic> authorResearchTopics;
	ArrayList<Article> authorAllocatedArticles;
	Researcher conferenceCoordinator;

	@Before
	public void setUp() throws Exception {
		// Construct new article for testing
		articleResearchTopic = new ResearchTopic("Software");
		articleID = 1;
		authorUniversity = new University("UFRGS");
		University reviewersUniversity = new University("USP");
		
		authorResearchTopics = new ArrayList<ResearchTopic>();
		authorAllocatedArticles = new ArrayList<Article>();
		
		articleReviewers = new ArrayList<Researcher>();
		articleReviewers.add(new Researcher(3, "Reviewer1", reviewersUniversity, new ArrayList<ResearchTopic>(), new ArrayList<Article>()));
		articleReviewers.add(new Researcher(4, "Reviewer2", reviewersUniversity, new ArrayList<ResearchTopic>(), new ArrayList<Article>()));
		
		conferenceCoordinator = new Researcher(2, "Coordinator", authorUniversity, authorResearchTopics,
				authorAllocatedArticles);
		author = new Researcher(1, "Jonn", authorUniversity, authorResearchTopics, authorAllocatedArticles);
		articleConference = new Conference("ConfName", authorAllocatedArticles, authorAllocatedArticles,
				articleReviewers, conferenceCoordinator);
		article = new Article(articleID, articleTitle, author, new ArrayList<Researcher>(), articleConference, articleResearchTopic, new HashMap<Researcher, Float>());

		System.out.print(article.toString());
	}

	@Test
	public void testID() {
		assertTrue(article.getID() == articleID);
	}

	@Test
	public void testAuthor() {
		assertTrue(article.getAuthorUniversity() == author.getUniversity());
	}

	@Test
	public void testResearchTopic() {
		assertTrue(article.getResearchTopic() == articleResearchTopic);
	}
	
	@Test
	public void testTitle() {
		assertTrue(article.getTitle() == articleTitle);
	}

	@Test
	public void testGrade() {

		assert (articleReviewers.size() == 2);
		// Add a reviewer
		assertTrue(article.numberOfReviewers() == 0);
		assertFalse(article.isResearcherAllocated(articleReviewers.get(0)));
		article.allocateReviewer(articleReviewers.get(0));
		assertTrue(article.numberOfReviewers() == 1);
		assertTrue(article.isResearcherAllocated(articleReviewers.get(0)));
		article.setGrade(articleReviewers.get(0), 2.0f);
		assertTrue(article.getGradeAverage() == 2.0f);

		// Test adding another reviewer
		assertFalse(article.isResearcherAllocated(articleReviewers.get(1)));
		article.allocateReviewer(articleReviewers.get(1));
		assertTrue(article.numberOfReviewers() == 2);
		assertTrue(article.isResearcherAllocated(articleReviewers.get(1)));
		article.setGrade(articleReviewers.get(1), 4.0f);
		assertTrue(article.getGradeAverage() == 3.0f); // (2+4)/2 = 3
	}

}