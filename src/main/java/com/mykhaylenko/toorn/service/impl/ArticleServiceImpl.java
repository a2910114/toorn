package com.mykhaylenko.toorn.service.impl;

import com.mykhaylenko.toorn.model.Article;
import com.mykhaylenko.toorn.repository.ArticleRepository;
import com.mykhaylenko.toorn.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 25.10.2015.
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    public Article findOneById(long id) {
        return articleRepository.findOne(id);
    }
}
