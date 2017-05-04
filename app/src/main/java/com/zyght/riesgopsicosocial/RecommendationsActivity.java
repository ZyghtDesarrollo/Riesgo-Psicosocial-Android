package com.zyght.riesgopsicosocial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.zyght.riesgopsicosocial.entity.Position;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Questionnaire;
import com.zyght.riesgopsicosocial.entity.Recommendation;
import com.zyght.riesgopsicosocial.handler.GetRecommendations;
import com.zyght.riesgopsicosocial.handler.LoginAPIHandler;
import com.zyght.riesgopsicosocial.network.ResponseActionDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class RecommendationsActivity extends AppCompatActivity implements ResponseActionDelegate{
    private static  final String TAG = "RecommendationsActivity";

    private QuestionBLL questionBLL = QuestionBLL.getInstance();
    private ListView mListView;
    private ArrayList<Recommendation> mDataSource = new ArrayList<>();
    private RecommendationsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recomendations);
        mListView = (ListView) findViewById(R.id.list_view);





        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView v = (TextView) view.findViewById(android.R.id.text1);
                //Toast.makeText(getApplicationContext(), "selected Item Name is " + v.getText(), Toast.LENGTH_LONG).show();

                show(position);
            }
        });


        GetRecommendations resourceHandler = new GetRecommendations();
        resourceHandler.setRequestHandle(this, this);


    }

    private void show(int position){
        Recommendation recommendation = mDataSource.get(position);


        String url = recommendation.getLink();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }


    @Override
    public void didSuccessfully(String message) {
        mDataSource = questionBLL.getRecommendations();
        adapter = new RecommendationsListAdapter(this, mDataSource);
        mListView.setAdapter(adapter);


    }

    @Override
    public void didNotSuccessfully(String message) {

    }
}
