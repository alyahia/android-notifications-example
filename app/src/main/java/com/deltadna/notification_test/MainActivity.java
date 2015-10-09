package com.deltadna.notification_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.deltadna.android.sdk.DDNA;
import com.deltadna.android.sdk.helpers.NotStartedException;


public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start the registrationIntentService manually upon start in order to get the registrationID
        Intent intent = new Intent(this, myRegistrationIntentService.class);
        startService(intent);

        // Initialise the deltaDNA SDK
        // NB This can also be done in your applications OnCreate method.
        DDNA.inst().init(this.getApplication());

        // Start deltaDNA SDK
        String environmentKey 	= "22079697190426055695055037414340";
        String collectHostname 	= "http://collect4792jmprb.deltadna.net/collect/api";
        String engageHostname 	= "http://engage4792jmprb.deltadna.net";

        // SDK generates its own userID when null is passed
        DDNA.inst().startSDK(environmentKey, collectHostname, engageHostname, null);

    }

    @Override
    public void onDestroy(){
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
