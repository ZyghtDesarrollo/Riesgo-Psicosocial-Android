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
import com.zyght.riesgopsicosocial.handler.RegisterRecomendationViewAPIHandler;
import com.zyght.riesgopsicosocial.network.ResponseActionDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class RecommendationsActivity extends AppCompatActivity implements ResponseActionDelegate, RecommendationsListAdapter.OnRecommendationListener{
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






        Position position = questionBLL.getSelectedPosition();


        GetRecommendations resourceHandler = new GetRecommendations(""+position.getId());
        resourceHandler.setRequestHandle(this, this);


    }



    @Override
    public void didSuccessfully(String message) {
        mDataSource = questionBLL.getRecommendations();
        adapter = new RecommendationsListAdapter(this, mDataSource, this);
        mListView.setAdapter(adapter);


    }

    @Override
    public void didNotSuccessfully(String message) {

    }

    @Override
    public void onRecommendationClick(Recommendation recommendation) {
        String url = recommendation.getLink();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        RegisterRecomendationViewAPIHandler handler = new RegisterRecomendationViewAPIHandler(recommendation);
        handler.setShowWaitingDialog(false);
        handler.setRequestHandle(this, this);

        startActivity(i);
    }
}
