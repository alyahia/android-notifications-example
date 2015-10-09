package com.deltadna.notification_test;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * This service extends the GcmListenerService and will be called upon receiving a GCM-message (push notification)
 * Created by steven on 29/09/15.
 * @author Steven van Stiphout
 */
public class MyGcmListenerService extends GcmListenerService{

    /**
     *This method handles the
     * @param from The senderId of the application in GCM
     * @param data the data received
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String TAG = "MyGcmListenerService";
        Log.i(TAG, "Push notification Received From: " + from);
        for (String key:data.keySet()){
            String msg = data.getString(key);
            Log.d(TAG, key+": "+msg);
        }
    }


    //TODO create an actual popup message instead of just a log message
}
