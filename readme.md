# Android Push Notification Demo using GCM and deltaDNA

This project is an android project intended as an example for android game developers who want to implement push notifications using googles GCM and deltaDNA's analytics platform.
See [DeltaDNA's documentation page](http://docs.deltadna.com/advanced-integration/android-sdk/google-cloud-messaging-setup/) for more information.

# Customisation
In order to test this application in your own environment you will have to do a few things:

 * Get the android configuration file as described on: https://developers.google.com/cloud-messaging/android/client and add it to your project.
 * Then log in to your deltaDNA account and add your public key to the Google identity.
 * Change the SDK integration in the MainActivity to the values provided by deltaDNA. (environment_key and collectAPI)

>The integration is done with the default events and should work out of the box in a development environment.

# Deployment
- Clone the project
 ```sh
$ git clone [repository url]
```
- Open in Android Studio
- Run