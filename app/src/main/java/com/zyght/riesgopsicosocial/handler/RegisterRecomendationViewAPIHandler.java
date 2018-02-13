package com.zyght.riesgopsicosocial.handler;


import android.util.Log;

import com.zyght.riesgopsicosocial.config.ResourcesConstants;
import com.zyght.riesgopsicosocial.entity.Recommendation;
import com.zyght.riesgopsicosocial.network.APIResourceHandler;
import com.zyght.riesgopsicosocial.network.APIResponse;
import com.zyght.riesgopsicosocial.network.HttpMethod;
import com.zyght.riesgopsicosocial.session.Session;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 3/24/17.
 */

public class RegisterRecomendationViewAPIHandler extends APIResourceHandler {


    private Recommendation recommendation;
    private String TAG = "RegisterRecomendationViewAPIHandler";

    public RegisterRecomendationViewAPIHandler(Recommendation recommendation) {

        this.recommendation = recommendation;

    }



    @Override
    public List<NameValuePair> getValueParams() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("random_user_id", Session.getInstance().getUser().getId()));
        nameValuePairs.add(new BasicNameValuePair("recommendation_id", String.valueOf(recommendation.getId())));
        nameValuePairs.add(new BasicNameValuePair("company_id", Session.getInstance().getUser().getCompanyId()));
        return nameValuePairs;
    }

    @Override
    public void handlerAPIResponse(APIResponse apiResponse) {

        if (apiResponse.getStatus().isSuccess()) {

            Log.i(TAG, "handlerAPIResponse"+ apiResponse.getRawResponse());


        } else {
            Log.e(TAG, "handlerAPIResponse"+ apiResponse.getRawResponse());
        }

    }




    @Override
    public String getServiceURL() {
        return ResourcesConstants.BASE_URL + "rrecommendation/register_recommendation_view";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
