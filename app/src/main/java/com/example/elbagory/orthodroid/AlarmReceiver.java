package com.example.elbagory.orthodroid;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import static com.example.elbagory.orthodroid.fragments.SurgeryFragment.PATIENT_ID;

/**
 * receive alarm and create a Notification
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("recive");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("1", "title", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);

        }
        Intent intent1 = new Intent(context, UpdatePatientActivity.class);
        intent1.putExtra(HomeActivity.PRIVATE_ID, intent.getIntExtra(PATIENT_ID,-1));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")

                .setSmallIcon(R.drawable.logo5)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentText("You have a surgical operation after an hour")
                .setContentIntent(PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            builder.setPriority(NotificationManager.IMPORTANCE_HIGH);

        notificationManager.notify(1, builder.build());


    }
}