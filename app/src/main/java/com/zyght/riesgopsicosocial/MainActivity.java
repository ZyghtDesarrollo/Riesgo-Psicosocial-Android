package com.zyght.riesgopsicosocial;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Questionnaire;
import com.zyght.riesgopsicosocial.handler.GetHasSurvey;
import com.zyght.riesgopsicosocial.network.ResponseActionDelegate;
import com.zyght.riesgopsicosocial.session.Session;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ResponseActionDelegate {


    private static final String TAG = "MainActivity";
    private ListView mListView;
    private ArrayList<Questionnaire> questionnaires;
    private boolean hasSurvey = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionnaires = QuestionBLL.getInstance().getQuestionnaires();
        mListView = (ListView) findViewById(R.id.list_view);

        String[] listItems = new String[questionnaires.size()+3];
        listItems[0] = "Equipo Psicosocial";

        int i=1;
        for (Questionnaire questionnaire : questionnaires){
            listItems[i] = questionnaire.getName();
            i++;
        }

        listItems[listItems.length-2] = "Recomendaciones";
        listItems[listItems.length-1] = "Bitácora de Procesos";


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // TextView v = (TextView) view.findViewById(android.R.id.text1);
                //Toast.makeText(getApplicationContext(), "selected Item Name is " + v.getText(), Toast.LENGTH_LONG).show();
                showNext(position);
            }
        });

        GetHasSurvey getHasSurvey = new GetHasSurvey();
        getHasSurvey.setRequestHandle(this, this);

    }

    private void showNext(int position){


        if(position == 0){
            Intent intent = new Intent(this, MembersActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, PositionActivity.class);


            if(position <= questionnaires.size()){
                Questionnaire questionnaire = questionnaires.get(0);
                intent.putExtra("QUESTIONNAIRE", questionnaire);
            }

            if(position ==1){
                if(hasSurvey){
                    Toast.makeText(this, "No puede volver a responder la encuesta", Toast.LENGTH_LONG).show();
                }else{
                    startActivity(intent);
                }
            }

            if(position == 2){
                startActivity(intent);
            }

            else if(position == 3){
                String url = "http://riesgopsicosocial.azurewebsites.net/index.php/billboard/show/"+ Session.getInstance().COMPANY_CODE;
                Intent intentP = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                Log.i(TAG, url);
                startActivity(intentP);
            }

        }

    }

    @Override
    public void didSuccessfully(String message) {
        hasSurvey = QuestionBLL.getInstance().HasSurvey();
    }

    @Override
    public void didNotSuccessfully(String message) {

    }
}
