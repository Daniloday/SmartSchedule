package com.missclick.smartschedule.data.datasource.local.dao

import androidx.room.*
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity

@Dao
interface DayDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDay(dayEntity: DayEntity)

    @Query("SELECT * FROM days")
    fun getAll() : List<DayEntity>

    @Query("SELECT * FROM days WHERE dayId = :id")
    fun getDayById(id : Int) : DayEntity

    @Delete
    fun removeDay(dayEntity: DayEntity)

    @Query("DELETE FROM days")
    fun deleteAllDays()
}