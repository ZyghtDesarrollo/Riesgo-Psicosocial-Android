package com.zyght.riesgopsicosocial.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class Question  implements Serializable {


    private List<Option> options;

    private int id, type;
    private String title, code;
    private int question_category_id, questionary_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuestion_category_id() {
        return question_category_id;
    }

    public void setQuestion_category_id(int question_category_id) {
        this.question_category_id = question_category_id;
    }

    public int getQuestionary_id() {
        return questionary_id;
    }

    public void setQuestionary_id(int questionary_id) {
        this.questionary_id = questionary_id;
    }


    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
