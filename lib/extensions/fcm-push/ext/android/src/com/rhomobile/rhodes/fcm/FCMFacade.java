/*------------------------------------------------------------------------
* (The MIT License)
*
* Copyright (c) 2008-2012 Rhomobile, Inc.
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*
* http://rhomobile.com
*------------------------------------------------------------------------*/

package com.rhomobile.rhodes.fcm;

import com.rhomobile.rhodes.Push;
import com.rhomobile.rhodes.Logger;
import com.rhomobile.rhodes.PushContract;
import com.rhomobile.rhodes.util.ContextFactory;
import com.rhomobile.rhodes.fcm.FCMIntentService;
import android.content.Intent;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.rhomobile.rhodes.R;
import android.util.Log;
import java.lang.Throwable;

public final class FCMFacade {
    private static final String TAG = FCMFacade.class.getSimpleName();
    
    static final String FCM_PUSH_CLIENT = "fcm";
    static String clientToken = "";

    private static String gStr(int key){
        String value = "";
        try{
            value = ContextFactory.getContext().getResources().getString(key);
        }catch(Exception e){
            Logger.W(TAG, "FCM: key " + key + " does not exist");
        }
        return value;
    }

    public static String google_app_id(){
        return gStr(R.string.google_app_id);
    }

    public static String gcm_defaultSenderId() {
        return gStr(R.string.gcm_defaultSenderId);
    }

/*
    public static String google_api_key(){
        return gStr(R.string.google_api_key);
    }
*/

    public static void initFireBase() {
        Log.d(TAG, "FCM: Send FCM push register req");
      
        try{
            FirebaseApp.getInstance();
            Logger.T(TAG, "FCM: Firebase inited");
        }catch(Exception exc){
            FirebaseOptions options = null;
            try {
                options = new FirebaseOptions.Builder()
                .setApplicationId(gStr(R.string.google_app_id))
                //.setApiKey(gStr(R.string.google_api_key))
                //.setDatabaseUrl(gStr(R.string.firebase_database_url))
                //.setStorageBucket(gStr(R.string.google_storage_bucket))
                .setGcmSenderId(gStr(R.string.gcm_defaultSenderId))
                .build();
            } catch( Throwable e ) {
                Logger.E(TAG, "FCM: poblems on building options: " + e);
                e.printStackTrace();
            }

            try{
                FirebaseApp.initializeApp(ContextFactory.getContext(), options);
                Logger.T(TAG, "FCM: initialization of application");
            }catch(Exception e){
                Logger.E(TAG, "FCM: poblems on initialization app: " + e);
                e.printStackTrace();
            }
            try{
                FirebaseApp.getInstance();
                Logger.T(TAG, "FCM: Firebase Inited");                
            }catch(Exception e){
                Logger.E(TAG, "FCM: poblems on getting instance: " + e);
                e.printStackTrace();
            }
        }   
  
        refreshToken();       
    }
    public static void refreshToken(){
        try{
            Log.d(TAG, "FCM: registation of application");
            clientToken = "";
            clientToken = FirebaseInstanceId.getInstance().getToken();
            if ((clientToken != "")&&(clientToken != null)){
                PushContract.handleRegistration(ContextFactory.getContext(), clientToken, FCMFacade.FCM_PUSH_CLIENT);
                Log.d(TAG, "FCM: registation successfully");
            }else{
                clientToken = "";
                Log.d(TAG, "FCM: can't get token, try to refresh later");
            }
        }catch(Exception exc){
            Log.d(TAG, "FCM: can't handle registation");
        }
        Log.d(TAG, "FCM: token = " + clientToken);
    }

}
