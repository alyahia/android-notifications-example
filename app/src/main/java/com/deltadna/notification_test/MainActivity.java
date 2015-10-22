package com.deltadna.notification_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.deltadna.android.sdk.DDNA;
import com.deltadna.android.sdk.helpers.NotStartedException;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DDNA MainActivity";
    public static final String PREFS_NAME = "PreferenceFile";
    public static TextView tokenTextView;
    public static TextView senderIDTextView;
    private SharedPreferences persistent_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persistent_settings = getSharedPreferences(PREFS_NAME, 0);

        //set the TokenTextView for later access
        tokenTextView = (TextView) findViewById(R.id.textViewLatestToken);

        //set senderID
        senderIDTextView = (TextView) findViewById(R.id.textViewSenderId);
        senderIDTextView.setText(getString(R.string.gcm_defaultSenderId));

        //Start the registrationIntentService manually upon start in order to get the registrationID
        this.startRegistrationIntent();

        // Initialise the deltaDNA SDK
        // NB This can also be done in your applications OnCreate method.
        DDNA.inst().init(this.getApplication());

        // Start deltaDNA SDK
        String environmentKey = "22079697190426055695055037414340";
        String collectHostname = "http://collect4792jmprb.deltadna.net/collect/api";
        String engageHostname = "http://engage4792jmprb.deltadna.net";

        // SDK generates its own userID when null is passed
        DDNA.inst().startSDK(environmentKey, collectHostname, engageHostname, null);
    }

    private void startRegistrationIntent() {
        if (persistent_settings.contains("token")) {
            //Since we already have a token we will only need to request a new one once it expires.
            //Token refresh will be initiated by the InstanceIdListenerService
            Log.d(TAG, "token already known, registration is not needed");
            String tokenText = persistent_settings.getString("token", "token not found");
            Log.d(TAG, tokenText);
            //log the token in the gui as well
            tokenTextView.setText(tokenText);
        } else {
            Intent intent = new Intent(this, MyRegistrationIntentService.class);
            startService(intent);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            DDNA.inst().stopSDK();
        } catch (NotStartedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
