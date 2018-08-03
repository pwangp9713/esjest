/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.domain.Article;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.ArticleMapperImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author root
 */
@RestController
public class ArticleController {
    @Autowired
    ArticleMapperImpl am;
    
    @RequestMapping("/createarticle")
    public String createarticle(int id, String title, String source, String topic, String content){
        am.createArticle(id, title, source, topic, content);
        return "success";
    }
    
    @RequestMapping("/searchtitle")
    public Article searchtitle(String title){
        return am.searchArticleTitle(title);
    }
    
    @RequestMapping("/searchsource")
    public List<Article> searchsource(String source){
        return am.searchArticleSource(source);
    }
    
    @RequestMapping("/searchcontent")
    public Article searchcontent(String content){
        return am.searchArticleContent(content);
    }
}
