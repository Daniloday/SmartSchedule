package com.missclick.smartschedule.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity

@Dao
interface LessonDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLesson(lessonEntity: LessonEntity)

    @Query("SELECT * FROM lessons")
    fun getAllLessons() : List<LessonEntity>
}