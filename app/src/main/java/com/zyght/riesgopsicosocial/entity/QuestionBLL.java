package com.zyght.riesgopsicosocial.entity;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class QuestionBLL {
    private static final QuestionBLL ourInstance = new QuestionBLL();
    public static final int SINGLE_OPTION = 1;
    public static final int NUMBER_OPTION = 2;


    private ArrayList<Answer> answers = new ArrayList<>();
    private ArrayList<Questionnaire> questionnaires = new ArrayList<>();




    public static QuestionBLL getInstance() {
        return ourInstance;
    }

    public QuestionBLL() {
    }


    public void add(Questionnaire questionnaire){
        questionnaires.add(questionnaire);
    }






    public void clear() {

        questionnaires.clear();

    }






    public  String[] getOptionsString(List<Option> optionsL) {

        String[] names = new String[optionsL.size()+1];

        names[0] ="";
        int i = 1;
        for (Option r : optionsL) {
            names[i] = r.getTitle();
            i++;
        }
        return names;
    }

    public ArrayList<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }



    public void add(Answer answer) {
        answers.add(answer);
    }



    private class  LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
