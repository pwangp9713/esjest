/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mapper;

import com.example.demo.domain.Article;
import com.example.demo.domain.Content;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */

@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Autowired
    JestClient jest;

    @Override
    public void createArticle(int id, String title, String source, String topic, String content) {

        Content c = new Content();
        c.setId(id);
        c.setContent(content);
        Index i0 = new Index.Builder(c).index("nyt").type("content").build();

        Article a = new Article();
        a.setId(id);
        a.setTitle(title);
        a.setSource(source);
        a.setTopic(topic);
        a.getContent().add(c);
        //c.setArticle(a);
        Index i1 = new Index.Builder(a).index("bbc").type("article").build();
        
        try {
            jest.execute(i0);
            jest.execute(i1);
        } catch (IOException ex) {
            Logger.getLogger(ArticleMapperImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Article searchArticleTitle(String title) {
        Article a = null;
        String json = "{\n"
                + "    \"query\" : {\n"
                + "        \"match\" : {\n"
                + "            \"title\" : \""+title+"\"\n"
                + "        }\n"
                + "    }\n"
                + "}";
        Search s = new Search.Builder(json).addIndex("bbc").addType("article").build();
        try {
            SearchResult r = jest.execute(s);
            a = r.getSourceAsObject(Article.class);
        } catch (IOException ex) {
            Logger.getLogger(ArticleMapperImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public List<Article> searchArticleSource(String source) {
        List<Article> a = null;
        String json = "{\n"
                + "    \"query\" : {\n"
                + "        \"match\" : {\n"
                + "            \"source\" : \""+source+"\"\n"
                + "        }\n"
                + "    }\n"
                + "}";
        Search s = new Search.Builder(json).addIndex("bbc").addType("article").build();
        try {
            SearchResult r = jest.execute(s);
            a = r.getSourceAsObjectList(Article.class);
        } catch (IOException ex) {
            Logger.getLogger(ArticleMapperImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public Article searchArticleContent(String content) {
        Article a = null;
        String json = "{\n"
                + "    \"query\" : {\n"
                + "        \"match\" : {\n"
                + "            \"about\" : \""+content+"\"\n"
                + "        }\n"
                + "    }\n"
                + "}";
        Search s = new Search.Builder(json).addIndex("bbc").addType("article").build();
        try {
            SearchResult r = jest.execute(s);
            a = r.getSourceAsObject(Article.class);
        } catch (IOException ex) {
            Logger.getLogger(ArticleMapperImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

}
