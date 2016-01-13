package com.mykhaylenko.toorn.model;

import javax.persistence.*;

/**
 * Created by User on 26.09.2015.
 */
@Entity
@Table(name = "article_content")
public class ArticleContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String link;

    @Lob
    private String text;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    public ArticleContent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
