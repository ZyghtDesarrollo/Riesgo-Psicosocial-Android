package com.zyght.riesgopsicosocial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zyght.riesgopsicosocial.entity.Answer;
import com.zyght.riesgopsicosocial.entity.Category;
import com.zyght.riesgopsicosocial.entity.Option;
import com.zyght.riesgopsicosocial.entity.Question;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Questionnaire;

import java.util.ArrayList;
import java.util.List;


public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

       // testJSON();
        showNextActivity();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }

    protected void showNextActivity() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    private void testJSON(){
        Gson gson = new Gson();

        Category category = new Category();
        category.setId(1);
        category.setTitle("Test");


        Question question0 = new Question();
        question0.setId(1);
        question0.setType(QuestionBLL.NUMBER_OPTION);
        question0.setQuestionary_id(1);
        question0.setTitle("¿Está preocupado por\n" +
                "si le cambian de tareas\n" +
                "contra su voluntad numerowe?");


        Option questionOptions  = new Option();
        questionOptions.setTitle("Siempre");
        questionOptions.setId(1);
        questionOptions.setQuestion_id(2);

        Option questionOptions2  = new Option();

        questionOptions2.setTitle("Sólo unas " +
                "pocas " +
                "veces");
        questionOptions2.setId(1);
        questionOptions2.setQuestion_id(2);


        ArrayList<Option> arrayListOption = new ArrayList<>();
        arrayListOption.add(questionOptions);
        arrayListOption.add(questionOptions2);

        question0.setOptions(arrayListOption);


        ArrayList<Question> arrayList = new ArrayList<>();
        arrayList.add(question0);

        category.setQuestions(arrayList);

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(1);
        questionnaire.setName("Corto");

        ArrayList<Category> categories = new ArrayList<>();
        categories.add(category);
        questionnaire.setCategories(categories);


        ArrayList<Questionnaire> questionnaires = new ArrayList<>();
        questionnaires.add(questionnaire);

        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setId(1);
        questionnaire1.setName("Largo");
        questionnaires.add(questionnaire1);

        String json = gson.toJson(questionnaires);

        Log.e("TEST", json);

        List<Questionnaire> list = gson.fromJson(json, new TypeToken<List<Questionnaire>>(){}.getType());

        for (Questionnaire q : list){
            Log.e("TEST", q.getName());
        }


        Answer answer = new Answer();

        answer.setQuestionId(1);
        answer.setQuestionOptionId(12);
        answer.setValue("");



        Answer answer1 = new Answer();

        answer1.setQuestionId(1);
        answer1.setQuestionOptionId(0);
        answer1.setValue("6 meses");


        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(answer);
        answers.add(answer1);


        String jsonA = gson.toJson(answers);

        Log.e("TEST", jsonA);
    }




    private static final int REQUEST_READ_CONTACTS = 0;




}
