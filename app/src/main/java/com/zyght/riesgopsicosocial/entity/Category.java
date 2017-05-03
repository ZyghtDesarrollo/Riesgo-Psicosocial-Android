package com.zyght.riesgopsicosocial.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class Category  implements Serializable {
    private int id;
    private String title;

    private List<Question> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
