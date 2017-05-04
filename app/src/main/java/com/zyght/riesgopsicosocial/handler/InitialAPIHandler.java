package com.zyght.riesgopsicosocial.handler;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zyght.riesgopsicosocial.entity.QuestionBLL;
import com.zyght.riesgopsicosocial.entity.Questionnaire;
import com.zyght.riesgopsicosocial.network.APIResourceHandler;
import com.zyght.riesgopsicosocial.network.APIResponse;
import com.zyght.riesgopsicosocial.network.HttpMethod;
import com.zyght.riesgopsicosocial.session.Session;
import com.zyght.riesgopsicosocial.session.User;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 3/24/17.
 */

public class InitialAPIHandler extends APIResourceHandler {






    @Override
    public void handlerAPIResponse(APIResponse apiResponse) {

        QuestionBLL questionBLL = QuestionBLL.getInstance();
        questionBLL.clear();

        if (apiResponse.getStatus().isSuccess()) {

            Gson gson = new Gson();
            String json = apiResponse.getRawResponse();

            List<Questionnaire> list = gson.fromJson(json, new TypeToken<List<Questionnaire>>(){}.getType());

            for (Questionnaire q : list){
                questionBLL.add(q);
            }

        }

        GetPositionsAPIHandler initialAPIHandler = new GetPositionsAPIHandler();
        initialAPIHandler.setRequestHandle(getResponseActionDelegate(), getContext());

    }




    @Override
    public String getServiceURL() {
        return ResourcesConstants.BASE_URL + "/rquestionary/initialdata";


    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
}
