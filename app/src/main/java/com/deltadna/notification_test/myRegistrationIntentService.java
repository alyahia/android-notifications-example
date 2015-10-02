package com.deltadna.notification_test;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by steven on 02/10/15.
 */
public class myRegistrationIntentService extends IntentService{
    public static String TAG = "myRegistrationIntentService";

    public myRegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        InstanceID instanceID = InstanceID.getInstance(this);

        try {
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            //successfully received a token so send it to server
            sendRegistrationToken(token);
        } catch (IOException e) {
            Log.w(TAG, e.getMessage());
        }
    }

    private void sendRegistrationToken(String token){
        //TODO implement sending token to push notification server
        Log.i(TAG, "GCM Registration Token: " + token);
    }
}
