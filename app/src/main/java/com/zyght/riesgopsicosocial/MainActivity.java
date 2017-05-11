package com.zyght.riesgopsicosocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Questionnaire;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;
    private ArrayList<Questionnaire> questionnaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionnaires = QuestionBLL.getInstance().getQuestionnaires();
        mListView = (ListView) findViewById(R.id.list_view);

        String[] listItems = new String[questionnaires.size()+1];

        int i=0;
        for (Questionnaire questionnaire : questionnaires){
            listItems[i] = questionnaire.getName();
            i++;
        }

        listItems[listItems.length-1] = "Recomendaciones";


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

    }

    private void showNext(int position){
        Intent intent = new Intent(this, PositionActivity.class);
        if(position < questionnaires.size()){
            Questionnaire questionnaire = questionnaires.get(position);
           intent.putExtra("QUESTIONNAIRE", questionnaire);
        }


        startActivity(intent);
    }
}
