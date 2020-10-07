package com.missclick.smartschedule.data.datasource.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class DayEntity (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "dayId")
    var id : Int? = null,

    @ColumnInfo(name = "dayName")
    var dayName : String,

    @ColumnInfo(name = "lessonId")
    var lessonId : Int,

    @ColumnInfo(name = "couple")
    var couple : Int

)