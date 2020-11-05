package com.missclick.smartschedule.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.repository.ILessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class CustomMessage {

    @Inject
    lateinit var repository: ILessonRepository

    init {
        App.appComponent.inject(this)
    }

    fun scheduleMsg(context: Context) { //Для запуска пушей вызвать это
        GlobalScope.launch(Dispatchers.IO) {
            val days = repository.getAllDays()
            for(day in days){
                val lesson = repository.getLessonById(day.lessonId)
                val calendar = Calendar.getInstance()
                calendar.setTimeInMillis(System.currentTimeMillis())
                val time = getTimeByCouple(day.couple)
                calendar.set(Calendar.MINUTE, 1, 6, time[0], time[1])
                lesson.id?.let { lesson.links["zoom"]?.let { it1 ->
                    scheduleMessage(
                        calendar, context, it,
                        it1
                    )
                } }
            }

        }

    }


    private fun scheduleMessage(calendar: Calendar, context: Context, type: Int, zoom: String) {
        val i = Intent(context, Receiver::class.java)
        i.putExtra(TYPE_EXTRA, type)
        i.putExtra("zoom", zoom)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            type,
            i,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManagerRTC = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManagerRTC.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY * 7 * 2, pendingIntent
        )
    }

    fun getNotificationManager(context: Context): Any? {
        return context.getSystemService(Context.NOTIFICATION_SERVICE)
    }

    fun getTimeByCouple(couple: Int) : List<Int>{
        if (couple == 1) return listOf(8, 20)
        if (couple == 2) return listOf(10, 15)
        return if (couple == 3) listOf(12, 10)
        else listOf(14, 5)
    }

    fun cancel(context: Context){
        val alarmManagerRTC = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0)
        alarmManagerRTC!!.cancel(pendingIntent)
    }

    companion object {
        const val TYPE_EXTRA = "type"
    }
}