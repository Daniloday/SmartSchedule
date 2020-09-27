package com.missclick.smartschedule.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.missclick.smartschedule.data.datasource.local.Dao.LessonDao
import com.missclick.smartschedule.data.datasource.local.Entity.Lesson

@Database(entities = [Lesson::class], version = 1)
abstract class ScheduleDatabase : RoomDatabase(){
    abstract fun lessonsDao() : LessonDao
}