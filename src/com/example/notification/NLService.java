package com.example.notification;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

public class NLService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();
    private NLServiceReceiver nlservicereciver;
    @Override
    public void onCreate() {
        super.onCreate();
        nlservicereciver = new NLServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.androidnotification.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
        registerReceiver(nlservicereciver,filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nlservicereciver);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        Log.i(TAG,"**********  onNotificationPosted");
        Log.i(TAG,"ID :" + sbn.getId() + "t" + sbn.getNotification().tickerText + "t" + sbn.getPackageName());
        Intent i = new  Intent("com.example.androidnotification.NOTIFICATION_LISTENER_EXAMPLE");
        Notification nf = sbn.getNotification();
        
        Bundle b = nf.extras;
        i.putExtra("Notification \n ","Notification Posted : \nPackage : " + sbn.getPackageName() +"\n notification : "
        		+ b.getCharSequence(nf.EXTRA_TEXT).toString() +"\n");
        
        
    //	Toast.makeText(getApplicationContext(), b.getCharSequence("EXTRA_BIG_TEXT"), Toast.LENGTH_SHORT).show();
       
        sendBroadcast(i);

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(TAG,"********** onNOtificationRemoved");
        Log.i(TAG,"ID :" + sbn.getId() + "t" + sbn.getNotification().tickerText +"t" + sbn.getPackageName());
        Intent i = new  Intent("com.example.androidnotification.NOTIFICATION_LISTENER_EXAMPLE");
        Notification nf = sbn.getNotification();
        Bundle b = nf.extras;
        i.putExtra("Notification \n ","Notification Posted : \nPackage : " + sbn.getPackageName() +"\n notification : "
        		+ b.getCharSequence(nf.EXTRA_TEXT).toString() +"\n");
        sendBroadcast(i);
    }

    class NLServiceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("command").equals("clearall")){
                    NLService.this.cancelAllNotifications();
            }
            else if(intent.getStringExtra("command").equals("list")){
                Intent i1 = new  Intent("com.example.androidnotification.NOTIFICATION_LISTENER_EXAMPLE");
                i1.putExtra("Notification : ","List\n");
                sendBroadcast(i1);
                int i=1;
                for (StatusBarNotification sbn : NLService.this.getActiveNotifications()) {
                	 Notification nf = sbn.getNotification();
                     Bundle b = nf.extras;
                    Intent i2 = new  Intent("com.example.androidnotification.NOTIFICATION_LISTENER_EXAMPLE");
                    i2.putExtra("Notification : ",i +" " + sbn.getPackageName() + "\n");
                    
                    sendBroadcast(i2);
                    i++;
                }
                Intent i3 = new  Intent("com.example.androidnotification.NOTIFICATION_LISTENER_EXAMPLE");
                i3.putExtra("Notification : ","Notification List\n");
                sendBroadcast(i3);

            }

        }
    }

}