package com.zyght.riesgopsicosocial.entity;

import java.io.Serializable;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class Option  implements Serializable {
    private int id, question_id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
