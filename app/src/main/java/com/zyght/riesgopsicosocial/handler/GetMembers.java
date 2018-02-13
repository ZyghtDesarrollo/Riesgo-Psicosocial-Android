package com.zyght.riesgopsicosocial.handler;


import android.util.Log;

import com.google.gson.Gson;
import com.zyght.riesgopsicosocial.config.ResourcesConstants;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.RpMember;
import com.zyght.riesgopsicosocial.network.APIResourceHandler;
import com.zyght.riesgopsicosocial.network.APIResponse;
import com.zyght.riesgopsicosocial.network.HttpMethod;
import com.zyght.riesgopsicosocial.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arley Mauricio Duarte on 3/24/17.
 */

public class GetMembers extends APIResourceHandler {




    @Override
    public void handlerAPIResponse(APIResponse apiResponse) {


        if (apiResponse.getStatus().isSuccess()) {

            extract(apiResponse.getRawResponse());

        }

        getResponseActionDelegate().didSuccessfully("");

    }


    private void extract(String apiResponse) {

        String response = "";

        QuestionBLL questionBLL = QuestionBLL.getInstance();
        questionBLL.getMembers().clear();

        try {
            JSONObject object = new JSONObject(apiResponse);
            response = object.getString("response");


            Gson gson = new Gson();

            RpMember[] arr = gson.fromJson(response, RpMember[].class);

            for (int i = 0; i < arr.length; i++) {
                RpMember q = arr[i];
                questionBLL.add(q);

            }

            Log.d("GetMembers", arr.toString());


        } catch (JSONException e) {
            Log.d("GetRecommendations", e.getMessage());
        }


    }


    @Override
    public String getServiceURL() {
        return ResourcesConstants.BASE_URL + "/rpsicomember/list_by_company_id?company_id="+Session.getInstance().getUser().getCompanyId()+"&active=true";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
}
