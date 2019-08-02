package techcourse.myblog.domain;

import techcourse.myblog.web.dto.ArticleRequestDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String coverUrl;
    private String contents;
    @ManyToOne
    private User author;

    private Article() {}

    public static Article of(Long id, String title, String coverUrl, String contents, User author) {
        Article newArticle = new Article();
        newArticle.id = id;
        newArticle.title = title;
        newArticle.coverUrl = coverUrl;
        newArticle.contents = contents;
        newArticle.author = author;
        return newArticle;
    }

    public static Article of(String title, String coverUrl, String content, User author) {
        return of(null, title, coverUrl, content, author);
    }

    public static Article from(ArticleRequestDto dto) {
        Article newArticle = new Article();
        newArticle.title = dto.getTitle();
        newArticle.coverUrl = dto.getCoverUrl();
        newArticle.contents = dto.getContents();
        return newArticle;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getContents() {
        return contents;
    }

    public void update(Article article) {
        title = article.getTitle();
        coverUrl = article.coverUrl;
        contents = article.contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
