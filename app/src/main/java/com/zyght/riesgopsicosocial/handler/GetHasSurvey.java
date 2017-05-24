package com.zyght.riesgopsicosocial.handler;


import android.util.Log;

import com.google.gson.Gson;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Recommendation;
import com.zyght.riesgopsicosocial.network.APIResourceHandler;
import com.zyght.riesgopsicosocial.network.APIResponse;
import com.zyght.riesgopsicosocial.network.HttpMethod;
import com.zyght.riesgopsicosocial.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arley Mauricio Duarte on 3/24/17.
 */

public class GetHasSurvey extends APIResourceHandler {




    @Override
    public void handlerAPIResponse(APIResponse apiResponse) {


        if (apiResponse.getStatus().isSuccess()) {

            extract(apiResponse.getRawResponse());

        }

        getResponseActionDelegate().didSuccessfully("");

    }


    private void extract(String apiResponse) {

        boolean response = false;

        QuestionBLL questionBLL = QuestionBLL.getInstance();


        try {
            JSONObject object = new JSONObject(apiResponse);
            response = object.getBoolean("response");

            questionBLL.setHasSurvey(response);





        } catch (JSONException e) {
            Log.d("GetRecommendations", e.getMessage());
        }


    }


    @Override
    public String getServiceURL() {
        return ResourcesConstants.BASE_URL + "/rquestionary/has_random_user_a_questionary?random_user_id="+Session.getInstance().getUser().getId();
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
}
