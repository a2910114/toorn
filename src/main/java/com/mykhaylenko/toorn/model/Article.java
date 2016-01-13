package com.mykhaylenko.toorn.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 26.09.2015.
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @OneToMany (mappedBy = "article", cascade = CascadeType.ALL)
    private List<ArticleContent> contents;

    public Article() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ArticleContent> getContents() {
        return contents;
    }

    public void setContents(List<ArticleContent> contents) {
        this.contents = contents;
    }
}
