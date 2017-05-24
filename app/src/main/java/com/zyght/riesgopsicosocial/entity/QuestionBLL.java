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
    private ArrayList<Recommendation> recommendations = new ArrayList<>();
    private ArrayList<RpMember> members = new ArrayList<>();

    private ArrayList<Questionnaire> questionnaires = new ArrayList<>();

    private ArrayList<Position> positions = new ArrayList<>();

    private boolean hasSurvey = false;

    public boolean HasSurvey() {
        return hasSurvey;
    }

    public void setHasSurvey(boolean hasSurvey) {
        this.hasSurvey = hasSurvey;
    }

    private Position selectedPosition;
    public ArrayList<RpMember> getMembers(){
        return  members;
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(Position selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public static QuestionBLL getInstance() {
        return ourInstance;
    }

    public QuestionBLL() {
    }


    public void add(Questionnaire questionnaire){
        questionnaires.add(questionnaire);
    }



    public void add(Position position){
        positions.add(position);
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


    public  String[] getPositionsString(List<Position> optionsL) {

        String[] names = new String[optionsL.size()+1];

        names[0] ="";
        int i = 1;
        for (Position r : optionsL) {
            names[i] = r.getPosition();
            i++;
        }
        return names;
    }

    public ArrayList<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }





    public ArrayList<Position> getPositions() {
        return positions;
    }

    public ArrayList<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void add(Recommendation recommendation){
        recommendations.add(recommendation);
    }

    public void add(RpMember q) {
        members.add(q);
    }
}
