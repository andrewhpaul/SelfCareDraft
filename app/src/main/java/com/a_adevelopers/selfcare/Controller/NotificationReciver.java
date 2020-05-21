package com.a_adevelopers.selfcare.Controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.a_adevelopers.selfcare.R;
import com.a_adevelopers.selfcare.db.SqlLite;

import static androidx.core.content.ContextCompat.getSystemService;

public class NotificationReciver extends BroadcastReceiver {
    SqlLite database;
    @Override
    public void onReceive(Context context, Intent intent) {
        database=new SqlLite( context );
        String name=intent.getStringExtra( "name" );
        String id=intent.getStringExtra( "id" );
        NotificationCompat.Builder builder=new NotificationCompat.Builder( context,"noti" )
                .setContentTitle(name)
                .setContentText(name+" is going to be expiry")
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from( context );
        notificationManagerCompat.notify( 200,builder.build() );
        database.deleteNoti( id);





    }
}
