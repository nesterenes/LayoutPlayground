package com.oneupapplications.layoutplayground.model;

import java.util.List;


public class JsonReturn {

    List<Article> articleList;
    List<String> categories;
    Integer count;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> jokeList) {
        this.articleList = articleList;
    }

//    public List<String> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<String> categories) {
//        this.categories = categories;
//    }
//
//    public Integer getCount() {
//        return count;
//    }
//
//    public void setCount(Integer count) {
//        this.count = count;
//    }

}
