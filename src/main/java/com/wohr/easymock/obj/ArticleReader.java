package com.wohr.easymock.obj;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArticleReader {

    private List<BaeldungArticle> articles;
    private Iterator<BaeldungArticle> articleIter;

    public ArticleReader() {
        this(new ArrayList<>());
    }

    public ArticleReader(List<BaeldungArticle> articles) {
        this.articles = articles;
        this.articleIter = this.articles.iterator();
    }

    public List<BaeldungArticle> ofTopic(String topic) {
        return articles
          .stream()
          .filter(article -> article
            .title()
            .contains(topic))
          .collect(toList());
    }

    public BaeldungArticle next() {
        return this.articleIter.next();
    }

}
