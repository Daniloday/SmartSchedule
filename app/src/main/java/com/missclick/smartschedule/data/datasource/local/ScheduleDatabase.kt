package com.missclick.smartschedule.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.missclick.smartschedule.data.datasource.local.dao.DayDao
import com.missclick.smartschedule.data.datasource.local.dao.LessonDao
import com.missclick.smartschedule.data.datasource.local.dao.SettingsDao
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.datasource.local.entity.SettingsEntity

@Database(entities = [LessonEntity::class, DayEntity::class, SettingsEntity::class], version = 11)
abstract class ScheduleDatabase : RoomDatabase(){
    abstract fun lessonsDao() : LessonDao
    abstract fun dayDao() : DayDao
    abstract fun settingsDao() : SettingsDao
}   