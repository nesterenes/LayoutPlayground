package com.oneupapplications.layoutplayground.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {

    public Category(int id, String title) {
        this.Id = id;
        this.Title = title;
    }

    private long Id;
    private String Title;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    //-------------------------

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }


    public static ArrayList<Category> ITEMS = new ArrayList<Category>() {
        {
            add(new Category(101, "Category 101"));
            add(new Category(102, "Category 102"));
            add(new Category(103, "Category 103"));
        }
    };

}
