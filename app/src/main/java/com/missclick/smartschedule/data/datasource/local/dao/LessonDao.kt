package com.missclick.smartschedule.data.datasource.local.dao

import androidx.room.*
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity

@Dao
interface LessonDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLesson(lessonEntity: LessonEntity)

    @Query("SELECT * FROM lessons")
    fun getAllLessons() : List<LessonEntity>

    @Query("SELECT * FROM lessons WHERE lessonId = :id")
    fun getLessonById(id : Int) : LessonEntity

    @Delete
    fun removeLesson(lessonEntity: LessonEntity)

    @Query("DELETE FROM lessons")
    fun deleteAllLessons()
}