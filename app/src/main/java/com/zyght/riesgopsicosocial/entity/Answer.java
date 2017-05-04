package com.zyght.riesgopsicosocial.entity;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class Answer {
    private int questionId;
    private  int questionOptionId;
    private String value="";

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(int questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
