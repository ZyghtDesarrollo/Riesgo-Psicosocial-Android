package com.zyght.riesgopsicosocial.handler;


import com.zyght.riesgopsicosocial.config.ResourcesConstants;
import com.zyght.riesgopsicosocial.network.APIResourceHandler;
import com.zyght.riesgopsicosocial.network.APIResponse;
import com.zyght.riesgopsicosocial.network.HttpMethod;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arley Mauricio Duarte on 3/24/17.
 */

public class SendQuestionnaireAPIHandler extends APIResourceHandler {

    private String answers;
    private String jobPositionId;
    private String questionnaire;

    public SendQuestionnaireAPIHandler(String answers, String jobPositionId, String questionnaire ) {

        this.answers = answers;
        this.jobPositionId = jobPositionId;
        this.questionnaire = questionnaire;
    }



    @Override
    public List<NameValuePair> getValueParams() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("answers", answers));
        nameValuePairs.add(new BasicNameValuePair("jobPositionId", jobPositionId));
        nameValuePairs.add(new BasicNameValuePair("questionaryId", questionnaire));
        return nameValuePairs;
    }

    @Override
    public void handlerAPIResponse(APIResponse apiResponse) {

        if (apiResponse.getStatus().isSuccess()) {
            getResponseActionDelegate().didSuccessfully(apiResponse.getRawResponse());



        } else {
            getResponseActionDelegate().didNotSuccessfully(apiResponse.getRawResponse());
        }

    }




    @Override
    public String getServiceURL() {
        return ResourcesConstants.BASE_URL + "/rquestionary/add";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
