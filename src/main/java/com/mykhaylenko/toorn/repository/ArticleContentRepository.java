package com.mykhaylenko.toorn.repository;

import com.mykhaylenko.toorn.model.Article;
import com.mykhaylenko.toorn.model.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by User on 26.09.2015.
 */
public interface ArticleContentRepository extends JpaRepository<ArticleContent, Long> {

    List<ArticleContent> findByArticle(Article article);
}
