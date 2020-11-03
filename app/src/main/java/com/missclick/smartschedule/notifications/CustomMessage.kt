package com.missclick.smartschedule.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import java.util.*


class CustomMessage {
    fun scheduleMsg(context: Context) { //Для запуска пушей вызвать это
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        scheduleMessage(calendar, context, 0)
    }

    private fun scheduleMessage(calendar: Calendar, context: Context, type: Int) {
        val i = Intent(context, Receiver::class.java)
        i.putExtra(TYPE_EXTRA, type)
        val pendingIntent = PendingIntent.getBroadcast(context, type, i, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManagerRTC = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val interval = 1000 * 60 * 60 * 8
        //Отправка через интервал
        alarmManagerRTC.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval,
            interval.toLong(), pendingIntent)
//        alarmManagerRTC[AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = pendingIntent
    }

    fun getNotificationManager(context: Context): Any? {
        return context.getSystemService(Context.NOTIFICATION_SERVICE)
    }

    companion object {
        const val TYPE_EXTRA = "type"
    }
}