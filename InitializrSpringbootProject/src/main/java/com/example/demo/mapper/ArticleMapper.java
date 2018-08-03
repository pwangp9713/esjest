/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mapper;

import com.example.demo.domain.Article;
import com.example.demo.domain.Content;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */

public interface ArticleMapper {
    void createArticle(int id, String title, String source, String topic , String content);
    Article searchArticleTitle(String title);
    List<Article> searchArticleSource(String source);
    Article searchArticleContent(String content);
}
