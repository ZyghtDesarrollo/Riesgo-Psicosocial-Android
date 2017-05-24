package com.zyght.riesgopsicosocial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zyght.riesgopsicosocial.entity.Answer;
import com.zyght.riesgopsicosocial.entity.Category;
import com.zyght.riesgopsicosocial.entity.Option;
import com.zyght.riesgopsicosocial.entity.Question;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Questionnaire;
import com.zyght.riesgopsicosocial.handler.SendQuestionnaireAPIHandler;
import com.zyght.riesgopsicosocial.network.ResponseActionDelegate;
import com.zyght.riesgopsicosocial.session.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class SurveyActivity extends AppCompatActivity implements ResponseActionDelegate {
    private static final String TAG = "SurveyActivity";
    private Spinner questionSpinner;
    private TextView questionText;
    private Question question;

    private int QUESTIONNAIRE = 1;
    private List<Option> optionsL;
    private EditText descriptionTextView;

    private QuestionBLL questionBLL = QuestionBLL.getInstance();

    private Questionnaire questionnaire;
    private Category category;
    private List<Category> categories;
    private ArrayList<Question> questions = new ArrayList<>();


    private ArrayList<Answer> answers = new ArrayList<>();

    private int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_survey);
        questionSpinner = (Spinner) findViewById(R.id.question_spinner);
        questionText = (TextView) findViewById(R.id.question_text);
        descriptionTextView = (EditText) findViewById(R.id.description_edit_text);

        Intent intent = getIntent();
        questionnaire = (Questionnaire) intent.getSerializableExtra("QUESTIONNAIRE");
        categories = questionnaire.getCategories();


        for (Category category : categories) {
            for (Question aQuestion : category.getQuestions()) {
                questions.add(aQuestion);
            }
        }

        fillData();


        AppCompatButton report = (AppCompatButton) findViewById(R.id.report_button);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }


        });

    }

    private void fillData() {


        if (!questions.isEmpty() && questionIndex < questions.size()) {
            question = questions.get(questionIndex);
            questionText.setText(question.getTitle());
            descriptionTextView.setText("");


            if (question.getType() == QuestionBLL.SINGLE_OPTION) {

                descriptionTextView.setVisibility(View.GONE);
                questionSpinner.setVisibility(View.VISIBLE);


                optionsL = question.getOptions();

                //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, questionBLL.getOptionsString(optionsL));
                //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_full_text, questionBLL.getOptionsString(optionsL));
                dataAdapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);

                questionSpinner.setAdapter(dataAdapter);
                dataAdapter.notifyDataSetChanged();

            }

            if (question.getType() == QuestionBLL.NUMBER_OPTION) {
                descriptionTextView.setVisibility(View.VISIBLE);
                questionSpinner.setVisibility(View.GONE);
            }

        }

    }

    private Answer getAnswer() {
        Answer response = null;
        if (isValidForm()) {

            response = new Answer();
            response.setQuestionId(question.getId());

            if (question.getType() == QuestionBLL.SINGLE_OPTION) {

                try {
                    int optionIndex = questionSpinner.getSelectedItemPosition();
                    Option selectedOption = optionsL.get(optionIndex - 1);
                    response.setQuestionOptionId(selectedOption.getId());

                    Log.e(TAG, "optionIndex:" + optionIndex + " Question:" + question.getId());
                } catch (Exception e) {

                    Log.e(TAG, e.getMessage());
                }

            }

            if (question.getType() == QuestionBLL.NUMBER_OPTION) {
                response.setValue(descriptionTextView.getText().toString());
            }

        }

        return response;
    }

    public void nextQuestion() {


        Answer answer = getAnswer();

        if (answer != null) {
            answers.add(answer);


            if (questionIndex == questions.size()-1) {
                saveData();


            } else {
                questionIndex++;
                fillData();
            }

        }


    }

    private void saveData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Guardar");
        builder.setMessage("¿Desea guardar y enviar la información de su encuesta?");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                send();
                dialog.dismiss();

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void send() {
        Gson gson = new Gson();


        String sAnswers = gson.toJson(answers);
        String jobPositionId = String.valueOf(questionBLL.getSelectedPosition().getId());
        String sQuestionnaireId = String.valueOf(questionnaire.getId());

        SendQuestionnaireAPIHandler resourceHandler = new SendQuestionnaireAPIHandler(sAnswers, jobPositionId, sQuestionnaireId);
        resourceHandler.setRequestHandle(this, this);
    }

    private boolean isValidForm() {
        boolean isValid = false;


        if (question.getType() == QuestionBLL.SINGLE_OPTION) {
            if (questionSpinner.getSelectedItem() != null && !TextUtils.isEmpty(questionSpinner.getSelectedItem().toString())) {
                isValid = true;
            } else {
                Toast.makeText(this, "Se debe seleccionar una respuesta", Toast.LENGTH_LONG).show();
            }

        }
        if (question.getType() == QuestionBLL.NUMBER_OPTION) {

            if (TextUtils.isEmpty(descriptionTextView.getText().toString())) {
                Toast.makeText(this, "Se debe  ingresar una respuesta", Toast.LENGTH_LONG).show();
                isValid = false;
            } else {
                isValid = true;
            }


        }


        return isValid;
    }


    @Override
    public void didSuccessfully(String message) {
        Toast.makeText(this, "Se ha enviado la información", Toast.LENGTH_LONG).show();
        questionBLL.setHasSurvey(true);
        finish();
    }

    @Override
    public void didNotSuccessfully(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
