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
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rhomobile.rhodes.util.ContextFactory;
import com.rhomobile.rhodes.Logger;
import com.rhomobile.rhodes.PushContract;
import com.rhomobile.rhodes.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class FCMIntentService extends FirebaseMessagingService {

    private static final String TAG = FCMIntentService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> params = new HashMap<String, String>(); 
        params.put("id", remoteMessage.getMessageId());
        params.put("from", remoteMessage.getFrom());
        params.put("to", remoteMessage.getTo());
        params.put("body", remoteMessage.getNotification().getBody());
        params.put("title", remoteMessage.getNotification().getTitle());
        params.put("tag", remoteMessage.getNotification().getTag());
        JSONObject jsonObject = new JSONObject(params);
        try {
            jsonObject.put("data", new JSONObject(remoteMessage.getData()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Logger.W(TAG, "FCM: push message: " + remoteMessage.getNotification().getBody());
        Logger.W(TAG, "FCM: push message in JSON: " + jsonObject.toString());
        PushContract.handleMessage(ContextFactory.getContext(), jsonObject.toString(), FCMFacade.FCM_PUSH_CLIENT);
    }
}
