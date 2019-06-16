package com.spiders.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleServiceRestController {

    @Autowired
    ArticleServiceDAO articleServiceDAO;

    /**
     * Metoda wyświetla listę wszsytkich artykułów w bazie.
     * Metoda : [GET]
     * URL    : /articles
     * SQL    : SELECT
     * TABALA : article
     *
     * @return
     */
    @RequestMapping(value="/articles", method=RequestMethod.GET)
    public List<Article> getArticles(){
        return articleServiceDAO.getArticles();
    }

    /**
     * Metoda wyświetla listę wszsytkich artykułów w bazie z określonym limitem.
     * Metoda : [GET]
     * URL    : /articles
     * SQL    : SELECT
     * TABALA : article
     *
     * @param limit limit wyświetlanych rekordów
     * @return
     */
    @RequestMapping(value="/articles", method=RequestMethod.GET)
    public List<Article> getArticlesLimit(@PathVariable int limit){
        return articleServiceDAO.getArticlesLimit(limit);
    }

    /**
     * Metoda pozwala na znalezienie i pokazanie artykułów o podanym w url tytule.
     * Metoda : [GET]
     * URL    : /searchedTitle/{title}
     * SQL    : SELECT
     * Tabela : article
     *
     * @param title title użytkownika podawane w adresie url.
     * @return
     */
    @RequestMapping(value = "/searchedTitle/{title}", method = RequestMethod.GET)
    public List<Article> getSearched(@PathVariable String title){
        return articleServiceDAO.getSearchedArticles(title);
    }

    /**
     * Metoda pozwala na znalezienie i pokazanie artykułów o podanym w url tytule oraz limicie wyświetlanych rekordw.
     * Metoda : [GET]
     * URL    : /searchedTitle/{title}
     * SQL    : SELECT
     * Tabela : article
     *
     * @param title title użytkownika podawane w adresie url.
     * @param limit limit wyświetlanych rekordów
     * @return
     */
    @RequestMapping(value = "/searchedTitle/{title}?limit={limit}", method = RequestMethod.GET)
    public List<Article> getSearchedLimit(@PathVariable String title, int limit){
        return articleServiceDAO.getSearchedArticlesLimit(title, limit);
    }

    /**
     * Metoda pozwala na znalezienie i pokazanie artykułu o podanym w url identyfikatorze.
     * Metoda : [GET]
     * URL    : /getArticleById/{id}
     * SQL    : SELECT
     * Tabela : article
     *
     * @param id Identyfikator podawany w adresie url.
     * @return
     */
    @RequestMapping(value = "/getArticleById/{id}", method = RequestMethod.GET)
    public List<Article> getArticleById(@PathVariable int id){
        return articleServiceDAO.getArticleById(id);
    }

    /**
     * Metoda pozwala na wprowadzenie nowego artykułu.
     * Metoda : [POST]
     * URL    : /articles
     * SQL    : INSERT
     * Tabela : article
     *
     * @param article Podany nowy artykuł
     * @return Informację że dodano poprawnie artykuł.
     */
    @RequestMapping(value="/articles", method=RequestMethod.POST) // zmien na POST
    public String insertUser(@RequestBody Article article){//walidacja
        articleServiceDAO.createArticle(article);
        return "Article created successfully";
    }

    /**
     * Metoda pozwala na aktualizację podanego artykułu o podanym w parametrze url identyfikatorze.
     * Metoda : [POST]
     * URL    : /articles/{id}
     * SQL    : UPDATE
     * Tabela : article
     *
     * @param article Podany artykuł, który będzie aktualizowany.
     * @param id Identyfikator podawany w adresie url.
     * @return Informację, że zaktualizowano poprawnie artykuł.
     */
    @RequestMapping(value="/articles/{id}", method=RequestMethod.POST)
    public String updateArticle(@RequestBody Article article, @PathVariable int id){
        articleServiceDAO.updateArticle(article, id);
        return "User updated successfully";
    }

    /**
     * Metoda pozwala na usunięcie podanego artykułu o podanym w parametrze url identyfikatorze.
     * Metoda : [POST]
     * URL    : /delArticle/{id}
     * SQL    : DELETE
     * Tabela : article
     *
     * @param id Identyfikator podawany w adresie url.
     * @return Informację, że usunięto poprawnie artykuł.
     */
    @RequestMapping(value = "/delArticle/{id}", method = RequestMethod.POST)
    public String addUser(@PathVariable int id){
        articleServiceDAO.deleteArticle(id);
        return "User deleted successfully";
    }


}
