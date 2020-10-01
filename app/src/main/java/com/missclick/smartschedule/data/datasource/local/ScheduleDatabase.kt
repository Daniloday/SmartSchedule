package com.missclick.smartschedule.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.missclick.smartschedule.data.datasource.local.dao.LessonDao
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity

@Database(entities = [LessonEntity::class], version = 2)
abstract class ScheduleDatabase : RoomDatabase(){
    abstract fun lessonsDao() : LessonDao
}