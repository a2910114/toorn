package com.mykhaylenko.toorn.repository;

import com.mykhaylenko.toorn.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 26.09.2015.
 */
public interface ArticleRepository extends JpaRepository<Article, Long>{
}
