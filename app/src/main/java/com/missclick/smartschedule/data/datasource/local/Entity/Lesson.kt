package com.missclick.smartschedule.data.datasource.local.Entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lessons")
data class Lesson (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "lessonId")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "week")
    var week : Int,

    @ColumnInfo(name = "day")
    var day : Int,

    @ColumnInfo(name = "time")
    var time : String,

    @ColumnInfo(name = "teacherName")
    var teacherName : String,

    @ColumnInfo(name = "contacts")
    var contacts : String,

    @ColumnInfo(name = "meet")
    var meet : String,

    @ColumnInfo(name = "task")
    var task : String,

    @ColumnInfo(name = "notes")
    var notes: String
)