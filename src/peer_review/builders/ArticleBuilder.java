package peer_review.builders;

import peer_review.models.ResearchTopic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import peer_review.models.Article;
import peer_review.models.Researcher;

public class ArticleBuilder {

	private Article article;
	 
    public ArticleBuilder() {
        article = new Article(1,
        		"article title",
				new ResearcherBuilder().name("author").build(),
				new ArrayList<>(Arrays.asList(new ResearcherBuilder().name("name 1").build(), new ResearcherBuilder().name("name 2").build())),
				new ConferenceBuilder().build(),
				new ResearchTopic("topic 1"),
				new HashMap<Researcher, Float>()
        		);
    }
 
    public ArticleBuilder id(int id) {
        article.setId(id);
        return this;
    }
 
    public ArticleBuilder title(String title) {
        article.setTitle(title);
        return this;
    }

    public ArticleBuilder grade(Researcher researcher, Float grade) {
        article.setGrade(researcher, grade);
        return this;
    }

    public ArticleBuilder author(Researcher author) {
        article.setAuthor(author);
        return this;
    }

    public Article build() {
        return article;
    }
}
