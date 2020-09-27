package com.missclick.smartschedule.data.datasource.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.missclick.smartschedule.data.datasource.local.Entity.Lesson

@Dao
interface LessonDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLesson(lessonDao: LessonDao)

    @Query("SELECT * FROM lessons")
    fun getAllLessons() : List<Lesson>
}