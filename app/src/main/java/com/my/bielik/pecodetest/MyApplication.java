package com.my.bielik.pecodetest;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build.*;

public class MyApplication extends Application {

    public static final String NOTIFICATION_CHANNEL_ID = "notification_channel";

    @Override
    public void onCreate() {
        super.onCreate();

        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "Notification channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }
}
