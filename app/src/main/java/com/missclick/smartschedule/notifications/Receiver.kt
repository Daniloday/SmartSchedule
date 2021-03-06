package com.missclick.smartschedule.notifications


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build
import android.R
import android.util.Log

import androidx.core.app.NotificationCompat;
import com.missclick.smartschedule.MainActivity


class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getIntExtra(CustomMessage.TYPE_EXTRA, 0)
        var zoom = intent.getStringExtra("zoom")
        var lessonName = intent.getStringExtra("lessonName")
        val intentToRepeat = Intent(context, MainActivity::class.java)
        intentToRepeat.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(context, type, intentToRepeat, 0) // FLAG_UPDATE_CURRENT
        val nm = CustomMessage().getNotificationManager(context)
        //todo check zoom nullable
        if(zoom == null) zoom = "zoom not detected, but lesson coming"
        else {
            Log.e("else",zoom)
            intentToRepeat.putExtra("notif", zoom)}
        if(lessonName == null) lessonName = "Not found lesson"
        val notification: Notification = configNotification(context, pendingIntent, nm as NotificationManager?, type, zoom, lessonName!!).build()
        nm?.notify(type, notification)
    }

    fun configNotification(context: Context, pendingIntent: PendingIntent?, nm: NotificationManager?, type : Int, zoom : String, lessonName : String): NotificationCompat.Builder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(type.toString(),
                "Daily Notification",
                NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Daily Notification"
            nm?.createNotificationChannel(channel)
        }
        //Создание пуша
        return NotificationCompat.Builder(context, "default")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_menu_my_calendar)
            .setContentTitle("$lessonName | Click to open zoom!")
//            .setContentTitle(zoom)
            .setAutoCancel(true)
    }
}