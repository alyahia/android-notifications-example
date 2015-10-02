package com.deltadna.notification_test;

import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.util.Log;

import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;


/**
 * Created by steven on 29/09/15.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {
    public static MediaSession.Token tokenItem;
    String TAG = "InstanceIdListenerService";

    public MyInstanceIDListenerService() {
        //This service will keep track of token changes and makes sure the registrationIntentService will request a new token when it needs to be refreshed
        Log.d(TAG, "InstanceId service started");

    }


    public void onTokenRefresh() {
        //Start the registrationIntentService since the token needs to be refreshed
        Intent regIntent = new Intent(this, myRegistrationIntentService.class);
        startService(regIntent);
    }

}
