package com.missclick.smartschedule.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.util.Log
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

                val calendar: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    val time = getTimeByCouple(day.couple)
                    val dayOfWeek = getDayOfWeek(day.dayName)
//                    val dayOfWeek = getDayOfWeek("kek")
                    set(Calendar.DAY_OF_WEEK, dayOfWeek)
                    set(Calendar.HOUR, time[0] - 12)
                    set(Calendar.MINUTE, time[1])
                }
                if(calendar.timeInMillis < System.currentTimeMillis()) {
                    calendar.add(Calendar.WEEK_OF_YEAR, day.week)
                }
                Log.e("Calendar", calendar.timeInMillis.toString() + " " + lesson.lessonName)
                lesson.id?.let {
                    scheduleMessage(
                        calendar, context, it,
                        lesson.links["zoom"].toString(),
                        lesson.lessonName
                    )
                }


            }

        }

    }


    private fun scheduleMessage(calendar: Calendar, context: Context, type: Int, zoom: String, lessonName : String) {
        val i = Intent(context, Receiver::class.java)
        i.putExtra(TYPE_EXTRA, type)
        i.putExtra("zoom", zoom)
        i.putExtra("lessonName", lessonName)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            type,
            i,
            0
        )
        val alarmManagerRTC = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManagerRTC.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            1000 * 3600 * 24 * 7 * 2  , pendingIntent
        )
    }

    fun getNotificationManager(context: Context): Any? {
        return context.getSystemService(Context.NOTIFICATION_SERVICE)
    }

    fun getTimeByCouple(couple: Int) : List<Int>{
        if (couple == 1) return listOf(8, 27)
        if (couple == 2) return listOf(14, 22)
        return if (couple == 3) listOf(12, 17)
        else listOf(14, 12)
    }

    private fun getDayOfWeek(day : String) : Int{
        return when(day) {
            "Monday" -> {
                Calendar.MONDAY
            }
            "Tuesday" -> {
                Calendar.TUESDAY
            }
            "Wednesday" -> {
                Calendar.WEDNESDAY
            }
            "Thursday" -> {
                Calendar.THURSDAY
            }
            "Friday" -> {
                Calendar.FRIDAY
            }
            "Saturday" -> {
                Calendar.SATURDAY
            }
            else -> {
                Calendar.SUNDAY
            }
        }
    }

    fun cancel(context: Context){
        val alarmManagerRTC = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0)
        alarmManagerRTC.cancel(pendingIntent)
    }

    companion object {
        const val TYPE_EXTRA = "type"
    }
}