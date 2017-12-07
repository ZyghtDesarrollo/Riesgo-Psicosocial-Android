package com.zyght.riesgopsicosocial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zyght.riesgopsicosocial.entity.Position;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Recommendation;
import com.zyght.riesgopsicosocial.entity.RpMember;
import com.zyght.riesgopsicosocial.handler.GetMembers;
import com.zyght.riesgopsicosocial.handler.GetRecommendations;
import com.zyght.riesgopsicosocial.network.ResponseActionDelegate;

import java.util.ArrayList;

/**
 * Created by Arley Mauricio Duarte on 5/2/17.
 */

public class MembersActivity extends AppCompatActivity implements ResponseActionDelegate, MembersListAdapter.EmailDelegate {
    private static  final String TAG = "MembersActivity";

    private QuestionBLL questionBLL = QuestionBLL.getInstance();
    private ListView mListView;
    private ArrayList<RpMember> mDataSource = new ArrayList<>();
    private MembersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recomendations);
        mListView = (ListView) findViewById(R.id.list_view);





        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });



        GetMembers resourceHandler = new GetMembers();
        resourceHandler.setRequestHandle(this, this);


    }




    @Override
    public void didSuccessfully(String message) {
        mDataSource = questionBLL.getMembers();
        adapter = new MembersListAdapter(this, mDataSource, this);
        mListView.setAdapter(adapter);


    }

    @Override
    public void didNotSuccessfully(String message) {

    }

    @Override
    public void emailOnClick(String email) {
        if(!TextUtils.isEmpty(email)){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain"); // send email as plain text
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Riesgos Psicosociales");

            startActivity(Intent.createChooser(intent, ""));
        }
    }




}
