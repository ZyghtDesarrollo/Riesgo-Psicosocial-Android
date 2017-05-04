package com.zyght.riesgopsicosocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.zyght.riesgopsicosocial.entity.Answer;
import com.zyght.riesgopsicosocial.entity.Option;
import com.zyght.riesgopsicosocial.entity.Position;
import com.zyght.riesgopsicosocial.entity.Question;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Questionnaire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class PositionActivity extends AppCompatActivity {
    private static  final String TAG = "PositionActivity";
    private Spinner positionSpinner;


    private List<Position> positions;
    private QuestionBLL questionBLL = QuestionBLL.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_position);
        positionSpinner = (Spinner) findViewById(R.id.question_spinner);


        fillData();


        AppCompatButton report = (AppCompatButton) findViewById(R.id.report_button);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }


        });

    }

    private void  fillData(){
        positions  = questionBLL.getPositions();

        if (!positions.isEmpty()) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_full_text, questionBLL.getPositionsString(positions));
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            positionSpinner.setAdapter(dataAdapter);
            dataAdapter.notifyDataSetChanged();

        }

    }


    public void next() {

        if(isValidForm()){
            int optionIndex = positionSpinner.getSelectedItemPosition();

            Position position = positions.get(optionIndex-1);

            questionBLL.setSelectedPosition(position);


            Intent intentO = getIntent();
            Questionnaire questionnaire =  (Questionnaire)intentO.getSerializableExtra("QUESTIONNAIRE");
            Intent intent = new Intent(this, SurveyActivity.class);
            intent.putExtra("QUESTIONNAIRE", questionnaire);
            startActivity(intent);
            finish();
        }

    }





    private boolean isValidForm() {
        boolean isValid = false;


        if (positionSpinner.getSelectedItem() != null && !TextUtils.isEmpty(positionSpinner.getSelectedItem().toString())) {
            isValid = true;
        }else{
            Toast.makeText(this, "Se debe seleccionar un cargo", Toast.LENGTH_LONG).show();
        }



        return isValid;
    }


}
