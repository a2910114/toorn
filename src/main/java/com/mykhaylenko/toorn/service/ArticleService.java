package com.mykhaylenko.toorn.service;

import com.mykhaylenko.toorn.model.Article;

/**
 * Created by User on 25.10.2015.
 */
public interface ArticleService {

    Article findOneById(long id);
}
