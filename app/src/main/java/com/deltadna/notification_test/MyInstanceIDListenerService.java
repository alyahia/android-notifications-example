package com.deltadna.notification_test;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;


/**
 * This service will keep track of the token refreshes, this means whenever a token is refreshed on the GCM side,
 * the app will need to retrieve it and report it back to its backend server.
 * Created by steven on 29/09/15.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {
    private static final String  TAG = "DDNA-InstanceIdLiServ";

    public MyInstanceIDListenerService() {
        //This service will keep track of token changes and makes sure the registrationIntentService will request a new token when it needs to be refreshed
        Log.d(TAG, "InstanceId service started");

    }


    public void onTokenRefresh() {
        //Start the registrationIntentService since the token needs to be refreshed
        Intent regIntent = new Intent(this, MyRegistrationIntentService.class);
        startService(regIntent);
    }

}
