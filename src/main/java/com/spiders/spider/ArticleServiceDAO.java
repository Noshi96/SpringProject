package com.spiders.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleServiceDAO {

    @Autowired
    JdbcTemplate jdbcTemplateArticle;




    public List<Article> getArticles(){
        List<Article> articleList = new ArrayList<>();
        String sql = "SELECT * FROM article";
        Collection<Map<String, Object>> rows = jdbcTemplateArticle.queryForList(sql);

        rows.stream().map((row)->{
            Article article = new Article();
            article.setId((Integer) row.get("id"));
            article.setArticleText((String) row.get("articleText"));
            article.setTitle((String) row.get("title"));
            article.setImage((String) row.get("image"));
            return article;
        }).forEach((article)->{

            articleList.add(article);
        });
        return articleList;
    }

    public void createArticle(Article article) {
        jdbcTemplateArticle.update((Connection connection)->{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO article (articleText,title,image) VALUES (?,?,?)");
            preparedStatement.setString(1, article.getArticleText());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getImage());
            return preparedStatement;
        });
    }

    public void updateArticle(Article article, int id) {
        jdbcTemplateArticle.update((Connection connection)->{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE article SET articleText=?, title=?, image=? WHERE id="+ id +"");
            preparedStatement.setString(1, article.getArticleText());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getImage());
            return preparedStatement;
        });
    }

    public List<Article> getSearchedArticles(String title){
        List<Article> articleList = new ArrayList<>();
        String sql = "SELECT id, articleText, title, image FROM article WHERE title LIKE '" + title + "%'";
        Collection<Map<String, Object>> rows = jdbcTemplateArticle.queryForList(sql);

        rows.stream().map((row)->{
            Article article = new Article();
            article.setId((Integer) row.get("id"));
            article.setArticleText((String) row.get("articleText"));
            article.setTitle((String) row.get("title"));
            article.setImage((String) row.get("image"));
            return article;
        }).forEach((article)->{
            articleList.add(article);
        });
        return articleList;
    }

    public List<Article> getArticleById(int id){
        List<Article> articleList = new ArrayList<>();
        String sql = "SELECT * FROM article WHERE id='"+ id +"'";
        Collection<Map<String, Object>> rows = jdbcTemplateArticle.queryForList(sql);

        rows.stream().map((row)->{
            Article article = new Article();
            article.setId((Integer) row.get("id"));
            article.setArticleText((String) row.get("articleText"));
            article.setTitle((String) row.get("title"));
            article.setImage((String) row.get("image"));
            return article;
        }).forEach((article)->{

            articleList.add(article);
        });
        return articleList;
    }
}
