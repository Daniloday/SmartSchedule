package com.missclick.smartschedule.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.missclick.smartschedule.data.datasource.local.dao.DayDao
import com.missclick.smartschedule.data.datasource.local.dao.LessonDao
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity

@Database(entities = [LessonEntity::class, DayEntity::class], version = 3)
abstract class ScheduleDatabase : RoomDatabase(){
    abstract fun lessonsDao() : LessonDao
    abstract fun dayDao() : DayDao
}