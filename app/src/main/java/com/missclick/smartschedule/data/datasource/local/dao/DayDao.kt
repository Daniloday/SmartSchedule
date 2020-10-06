package com.missclick.smartschedule.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity

@Dao
interface DayDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDay(dayEntity: DayEntity)

    @Query("SELECT * FROM days")
    fun getAll() : List<DayEntity>

    @Query("SELECT * FROM days WHERE dayId = :id")
    fun getDayById(id : Int) : DayEntity
}