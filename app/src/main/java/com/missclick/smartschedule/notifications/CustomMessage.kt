package com.missclick.smartschedule.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ShortLessonModel
import java.util.*


class CustomMessage {
    fun scheduleMsg(context: Context) { //Для запуска пушей вызвать это
        val calendar = Calendar.getInstance()
        //calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.MINUTE, 11,5,23, 9)
        scheduleMessage(calendar, context, 0)
        calendar.set(Calendar.MINUTE, 11,5,23, 9, 30)
        scheduleMessage(calendar, context, 1)
    }

//    private fun createCalendar(): Calendar {
//        val calendar: Calendar = Calendar.getInstance()
//        calendar.set(Calendar.MINUTE, 11,5,22, 55)
//        calendar.set(Calendar.MINUTE, 11,5,22, 54)
//        //calendars.add(calendar)
//        return calendar
//    }

    private fun scheduleMessage(calendar: Calendar, context: Context, type: Int) {
        val i = Intent(context, Receiver::class.java)
        i.putExtra(TYPE_EXTRA, type)
        val pendingIntent = PendingIntent.getBroadcast(context, type, i, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManagerRTC = context.getSystemService(ALARM_SERVICE) as AlarmManager
        //val interval = 1000 * 60 * 60 * 8
        //Отправка через интервал
        alarmManagerRTC.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            60 * 1000, pendingIntent)
        //alarmManagerRTC[AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = pendingIntent
    }

    fun getNotificationManager(context: Context): Any? {
        return context.getSystemService(Context.NOTIFICATION_SERVICE)
    }

    companion object {
        const val TYPE_EXTRA = "type"
    }
}