package com.zyght.riesgopsicosocial.handler;


import com.google.gson.Gson;
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

public class LoginAPIHandler extends APIResourceHandler {

    private String password;
    private String code;

    public LoginAPIHandler(String password, String code) {

        this.password = password;
        this.code = code;
    }


    public boolean isNeedAuthToken() {
        return false;
    }

    @Override
    public List<NameValuePair> getValueParams() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("code", code));
        return nameValuePairs;
    }

    @Override
    public void handlerAPIResponse(APIResponse apiResponse) {

        //TODO:
        InitialAPIHandler initialAPIHandler = new InitialAPIHandler();
        initialAPIHandler.setRequestHandle(getResponseActionDelegate(), getContext());

        /*
        if (apiResponse.getStatus().isSuccess()) {
            extractToken(apiResponse.getRawResponse());




        } else {
            getResponseActionDelegate().didNotSuccessfully(apiResponse.getStatus().getErrorCode());
        }
*/
    }


    public void extractToken(String apiResponse) {

        String token = "";

        try {
            JSONObject object = new JSONObject(apiResponse);
            token = object.getString("access_token");


            Gson gson = new Gson();

            User user = gson.fromJson(object.getString("user"), User.class);
            Session.getInstance().setUser(user);

        } catch (JSONException e) {

        } finally {
            Session.getInstance().setAccessToken(token, getContext());
        }


    }

    @Override
    public String getServiceURL() {
        //return ResourcesConstants.BASE_URL + "/token";

        return "http://trayectoseguro.azurewebsites.net/index.php/api/ruser/login";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
