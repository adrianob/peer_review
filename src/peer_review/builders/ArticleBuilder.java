package peer_review.builders;

import peer_review.models.ResearchTopic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import peer_review.models.Article;
import peer_review.models.Conference;
import peer_review.models.Review;
import peer_review.models.Researcher;

public class ArticleBuilder {

	private Article article;
	 
    public ArticleBuilder() {
        article = new Article(1,
        		"article title",
				new ResearcherBuilder().name("author").build(),
				new ConferenceBuilder().build(),
				new ResearchTopic("topic 1"),
				new ArrayList<Review>()
        		);
    }
 
    public ArticleBuilder(Conference conference) {
        article = new Article(1,
        		"article title",
				new ResearcherBuilder().name("author").build(),
				conference,
				new ResearchTopic("topic 1"),
				new ArrayList<Review>()
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
        article.addReview(researcher, Optional.ofNullable(grade));
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
