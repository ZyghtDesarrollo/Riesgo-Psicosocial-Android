package com.zyght.riesgopsicosocial.handler;


import android.util.Log;

import com.google.gson.Gson;
import com.zyght.riesgopsicosocial.entity.Position;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.network.APIResourceHandler;
import com.zyght.riesgopsicosocial.network.APIResponse;
import com.zyght.riesgopsicosocial.network.HttpMethod;
import com.zyght.riesgopsicosocial.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arley Mauricio Duarte on 3/24/17.
 */

public class GetPositionsAPIHandler extends APIResourceHandler {


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
        questionBLL.getPositions().clear();

        try {
            JSONObject object = new JSONObject(apiResponse);
            response = object.getString("response");


            Gson gson = new Gson();

            Position[] arr = gson.fromJson(response, Position[].class);

            for (int i = 0; i < arr.length; i++) {
                Position q = arr[i];
                questionBLL.add(q);

            }

            Log.d("GetPositionsAPIHandler", arr.toString());


        } catch (JSONException e) {
            Log.d("GetPositionsAPIHandler", e.getMessage());
        }


    }


    @Override
    public String getServiceURL() {
        return ResourcesConstants.BASE_URL + "/rjobposition/list_by_company_id?company_id=" + Session.getInstance().getUser().getCompanyId();
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
}
