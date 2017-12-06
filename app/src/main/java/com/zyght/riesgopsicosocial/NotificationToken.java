package com.zyght.riesgopsicosocial;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by Arley Mauricio Duarte on 6/1/17.
 */

public class NotificationToken {

    private String refreshedToken = "";
    private static final NotificationToken ourInstance = new NotificationToken();

    public static NotificationToken getInstance() {
        return ourInstance;
    }

    private NotificationToken() {
    }

    public String getRefreshedToken(Context context) {

        if(TextUtils.isEmpty(refreshedToken)){
            SharedPreferences userDetails = context.getSharedPreferences("userdetails", Context.MODE_PRIVATE);
            refreshedToken = userDetails.getString("refreshedToken", "");
        }



        return refreshedToken;
    }

    public static NotificationToken getOurInstance() {
        return ourInstance;
    }

    public void setRefreshedToken(Context context, String refreshedToken) {
        this.refreshedToken = refreshedToken;

        if(!TextUtils.isEmpty(refreshedToken)){
            SharedPreferences sharedPref =  context.getSharedPreferences("userdetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(refreshedToken, "refreshedToken");
            editor.apply();
        }


    }
}
