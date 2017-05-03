package com.zyght.riesgopsicosocial.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class Questionnaire implements Serializable {
    private int id;
    private String name;

    private List<Category> categories;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
