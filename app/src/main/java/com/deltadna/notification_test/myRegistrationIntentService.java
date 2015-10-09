package com.deltadna.notification_test;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by steven on 02/10/15.
 * This service gets the registration ID token and will be triggered whenever the token needs to be refreshed.
 * @author Steven van Stiphout
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

    /**
     * send the registration token to the backend that is responsible for sending push notifications
     * @param token
     */
    private void sendRegistrationToken(String token){
        //TODO implement sending token to push notification server
        Log.i(TAG, "GCM Registration Token: " + token);
    }
}
