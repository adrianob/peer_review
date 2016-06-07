package peer_review.tests;

/*
 * 	private int id;
	private String name;
	private University affiliation;
	private arrayList<ResearchTopic> researchTopic;
	public arrayList<Article> allocatedArticles;
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import peer_review.builders.ArticleBuilder;
import peer_review.builders.ResearcherBuilder;
import peer_review.models.Researcher;

public class ResearcherTest {
	Researcher researcher;
	
	@Before
	public void setUp() throws Exception {
		researcher = new ResearcherBuilder().name("name 1").build();
		System.out.print(researcher.toString());
	}
	
	@Test
	public void testGetResearchTopics() {
	//	assertTrue(researcher.researchTopic == researcher.getResearchTopics());
	}
	@Test
	public void testGetUniversity() {
		//assertTrue(affiliation == researcher1.getUniversity());
		
	}
	@Test
	public void testGetName() {
		//assertTrue(name == researcher1.getName());
	}
	@Test
	public void testGetID() {
		assertTrue(researcher.getID() == 1);
	}
	@Test
	public void testAllocateArticle() {
		
	}
}
