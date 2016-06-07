
package peer_review.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import peer_review.models.Article;
import peer_review.models.Grade;
import peer_review.models.Conference;
import peer_review.models.Researcher;
import peer_review.models.University;
import peer_review.models.ResearchTopic;

public class Database {

	private final Map<Integer, Researcher> researchers;
	private final Map<Integer, Article> articles;
	private final Map<String, Conference> conferences;
	private final Map<String, University> universities;
	private final Map<String, ResearchTopic> researchTopics;
	
	public Database() {
		this(true);
	}

	public Database(boolean initData) {				
		this.articles = new HashMap<>();
		this.researchers = new HashMap<>();
		this.conferences = new HashMap<String, Conference>();
		this.universities = new HashMap<String, University>();
		this.researchTopics = new HashMap<String, ResearchTopic>();
		
		if (initData) {
			initData();
		}
	}

	public Collection<University> getUniversities() {
		return this.universities.values();
	}

	public Collection<ResearchTopic> getResearchTopics() {
		return this.researchTopics.values();
	}

	public Collection<Article> getArticles() {
		return this.articles.values();
	}

	public Collection<Conference> getConferences() {
		return this.conferences.values();
	}

	public Collection<Researcher> getResearchers() {
		return this.researchers.values();
	}

	private void initData() {
		//adding research topics
		ResearchTopic modularity = new ResearchTopic("Modularity");
		add(modularity);
		ResearchTopic softwareReuse = new ResearchTopic("Software Reuse");
		add(softwareReuse);
		ResearchTopic aspectOrientedProgramming = new ResearchTopic("Aspect-oriented Programming");
		add(aspectOrientedProgramming);
		ResearchTopic softwareProductLine = new ResearchTopic("Software	Product	Lines");
		add(softwareProductLine);
		ResearchTopic softwareArchitecture = new ResearchTopic("Software Architecture");
		add(softwareArchitecture);
		ResearchTopic softwareTesting = new ResearchTopic("Software	Testing");
		add(softwareTesting);
		ResearchTopic softwareQuality = new ResearchTopic("Software	Quality");
		add(softwareQuality);
		
		//adding universities
		University ufrgs = new University("UFRGS");
		add(ufrgs);
		University usp = new University("USP");
		add(usp);
		University ufrj = new University("UFRJ");
		add(ufrj);

		//adding researchers
		Researcher researcher1 = new Researcher(1, "João", ufrgs, 
				new ArrayList<>(Arrays.asList(modularity, softwareReuse, softwareProductLine)));
		add(researcher1);
		Researcher researcher2 = new Researcher(2, "Ana", usp, 
				new ArrayList<>(Arrays.asList(modularity, softwareReuse, softwareArchitecture)));
		add(researcher2);
		Researcher researcher3 = new Researcher(3, "Manoel", ufrgs, 
				new ArrayList<>(Arrays.asList(softwareProductLine, softwareTesting)));
		add(researcher3);
		Researcher researcher4 = new Researcher(4, "Joana", ufrj, 
				new ArrayList<>(Arrays.asList(softwareProductLine, softwareReuse, softwareArchitecture, aspectOrientedProgramming)));
		add(researcher4);
		Researcher researcher5 = new Researcher(5, "Miguel", ufrgs, 
				new ArrayList<>(Arrays.asList(modularity, softwareTesting, softwareArchitecture)));
		add(researcher5);
		Researcher researcher6 = new Researcher(6, "Beatriz", ufrj, 
				new ArrayList<>(Arrays.asList(softwareReuse, softwareTesting, aspectOrientedProgramming)));
		add(researcher6);
		Researcher researcher7 = new Researcher(7, "Suzana", ufrgs, 
				new ArrayList<>(Arrays.asList(aspectOrientedProgramming, modularity, softwareReuse)));
		add(researcher7);
		Researcher researcher8 = new Researcher(8, "Natasha", ufrj, 
				new ArrayList<>(Arrays.asList(modularity, softwareReuse, softwareQuality, softwareProductLine)));
		add(researcher8);
		Researcher researcher9 = new Researcher(9, "Pedro", usp, 
				new ArrayList<>(Arrays.asList(aspectOrientedProgramming, softwareArchitecture)));
		add(researcher9);
		Researcher researcher10 = new Researcher(10, "Carlos", usp, 
				new ArrayList<>(Arrays.asList(softwareReuse, modularity, softwareTesting)));
		add(researcher10);
		
		//adding conferences
		Conference icse = new Conference("ICSE", 
				new ArrayList<Researcher>(Arrays.asList(researcher1, researcher2, researcher3, researcher4, researcher5, researcher6, researcher7)));
		add(icse);
		
		Conference fse = new Conference("FSE", 
				new ArrayList<Researcher>(Arrays.asList(researcher1, researcher2, researcher3, researcher4, researcher5, researcher6, researcher7)));
		add(fse);

		Conference sbes = new Conference("SBES", 
				new ArrayList<Researcher>(Arrays.asList(researcher4, researcher5, researcher6, researcher7, researcher8, researcher9, researcher10)));
		add(sbes);

		//adding articles
		Article article1 = new Article(1, "Coupling	and	Cohesion", researcher1, 
				new ArrayList<Researcher>(Arrays.asList(researcher8, researcher10)), sbes, modularity, new ArrayList<Grade>());
		article1.setGrade(researcher8, Optional.of(2.0f));
		article1.setGrade(researcher10, Optional.ofNullable(null));
		add(article1);
		Article article2 = new Article(2, "Design Patterns", researcher6, 
				new ArrayList<Researcher>(Arrays.asList(researcher7, researcher2)), fse, softwareReuse, new ArrayList<Grade>());
		article2.setGrade(researcher7, Optional.of(2.0f));
		article2.setGrade(researcher2, Optional.of(3.0f));
		add(article2);
		Article article3 = new Article(3, "AspectJ", researcher7, 
				new ArrayList<Researcher>(Arrays.asList(researcher4, researcher6)), fse, aspectOrientedProgramming, new ArrayList<Grade>());
		article3.setGrade(researcher4, Optional.of(-1.0f));
		article3.setGrade(researcher6, Optional.of(1.0f));
		add(article3);
		Article article4 = new Article(4, "Feature Model", researcher8, 
				new ArrayList<Researcher>(Arrays.asList(researcher1, researcher3)), fse, softwareProductLine, new ArrayList<Grade>());
		article4.setGrade(researcher1, Optional.of(1.0f));
		article4.setGrade(researcher3, Optional.of(0.0f));
		add(article4);
		Article article5 = new Article(5, "Architecture	Recovery", researcher9, 
				new ArrayList<Researcher>(Arrays.asList(researcher4, researcher5)), fse, softwareArchitecture, new ArrayList<Grade>());
		article5.setGrade(researcher4, Optional.of(-3.0f));
		article5.setGrade(researcher5, Optional.of(-3.0f));
		add(article5);
		Article article6 = new Article(6, "Funcional Testing", researcher10, 
				new ArrayList<Researcher>(Arrays.asList(researcher3, researcher6)), fse, softwareTesting, new ArrayList<Grade>());
		article6.setGrade(researcher3, Optional.of(-1.0f));
		article6.setGrade(researcher6, Optional.of(0.0f));
		add(article6);
		Article article7 = new Article(7, "COTs", researcher6, 
				new ArrayList<Researcher>(), icse, softwareReuse, new ArrayList<Grade>());
		add(article7);
		Article article8 = new Article(8, "Pointcut", researcher7, 
				new ArrayList<Researcher>(), icse, aspectOrientedProgramming, new ArrayList<Grade>());
		add(article8);
		Article article9 = new Article(9, "Architecture	Comformance", researcher8, 
				new ArrayList<Researcher>(), icse, softwareProductLine, new ArrayList<Grade>());
		add(article9);
		Article article10 = new Article(10, "Design	Patterns", researcher9, 
				new ArrayList<Researcher>(), icse, softwareArchitecture, new ArrayList<Grade>());
		add(article10);
		Article article11 = new Article(11, "Structural	Testing", researcher10, 
				new ArrayList<Researcher>(), icse, softwareTesting, new ArrayList<Grade>());
		add(article11);
	}

	public void add(Article article) {
		this.articles.put(article.getID(), article);
	}

	public void add(Conference conference) {
		this.conferences.put(conference.getInitials(), conference);
	}

	public void add(University university) {
		this.universities.put(university.getName(), university);
	}

	public void add(ResearchTopic researchTopic) {
		this.researchTopics.put(researchTopic.getName(), researchTopic);
	}

	public void add(Researcher researcher) {
		this.researchers.put(researcher.getID(), researcher);
	}
	
	public Article getArticleById(int id){
		return this.articles.get(id);
	}

	public Conference getConferenceByInitials(String initials){
		return this.conferences.get(initials);
	}

	public Researcher getResearcherById(int id){
		return this.researchers.get(id);
	}
}
