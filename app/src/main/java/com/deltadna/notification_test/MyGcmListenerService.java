package com.deltadna.notification_test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * This service extends the GcmListenerService and will be called upon receiving a GCM-message (push notification)
 * Created by steven on 29/09/15.
 *
 * @author Steven van Stiphout
 */
public class MyGcmListenerService extends GcmListenerService {
    /**
     * This method handles the
     *
     * @param from The senderId of the application in GCM
     * @param data the data received
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String TAG = "MyGcmListenerService";
        Log.i(TAG, "Push notification Received From: " + from);
        for (String key : data.keySet()) {
            String msg = data.getString(key);
            Log.d(TAG, key + ": " + msg);
        }
        createNotification(data.getString("alert"));
    }


    //TODO create an actual popup message instead of just a log message

    private void createNotification(String msg) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(msg)
                        .setContentText("This is a notification sent by DeltaDNA");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        //set the AutoCancel in order for the notification to disappear on opening.
        mBuilder.setAutoCancel(true);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on, this is a unique identifier within this app for the notification.
        int mId = 1;
        mNotificationManager.notify(mId, mBuilder.build());

    }
}
