package com.mykhaylenko.toorn.controller;

import com.mykhaylenko.toorn.model.Article;
import com.mykhaylenko.toorn.model.ArticleContent;
import com.mykhaylenko.toorn.repository.ArticleContentRepository;
import com.mykhaylenko.toorn.repository.ArticleRepository;
import com.mykhaylenko.toorn.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by pavlo.mykhaylenko on 8/13/2015.
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleContentRepository articleContentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String messages(Model model) {
        List<Article> articles = articleRepository.findAll();

        for (Article article : articles) {
            List<ArticleContent> content = articleContentRepository.findByArticle(article);
            article.setContents(content);
        }

        model.addAttribute("articles", articles);
        return "articles";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getOneMessage(Model model, @PathVariable("id") long id) {
        Article article = articleService.findOneById(id);
        List<ArticleContent> content = articleContentRepository.findByArticle(article);
        article.setContents(content);
        model.addAttribute("article", article);

        return "articleEdit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editOneMessage(@ModelAttribute Article article) {
        List<ArticleContent> contents = article.getContents();
        for (ArticleContent content : contents) {
            content.setArticle(article);
//            articleContentRepository.delete(content.getId());
        }
        List<ArticleContent> byArticle = articleContentRepository.findByArticle(article);
        for (ArticleContent content : byArticle) {
            articleContentRepository.delete(content.getId());
        }


        articleRepository.save(article);

        return "redirect:/articles";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("article", new Article());

        return "articleCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processRegistration(Article article) {

        List<ArticleContent> contents = article.getContents();
        for (ArticleContent content : contents) {
            content.setArticle(article);
        }
        articleRepository.save(article);
        articleContentRepository.save(contents);

        return "redirect:/articles";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteArticle(@PathVariable("id") long id) {
        articleRepository.delete(id);

        return "redirect:/articles";
    }

    @RequestMapping(value = "/deletelink/{id}", method = RequestMethod.GET)
    public String deleteLink(@PathVariable("id") long id) {
        articleContentRepository.delete(id);

        return "redirect:/articles";
    }
}
