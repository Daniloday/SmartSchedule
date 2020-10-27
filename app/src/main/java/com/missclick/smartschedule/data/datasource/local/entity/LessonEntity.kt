package com.missclick.smartschedule.data.datasource.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "lessonId")
    var id : Int? = null,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "teacherName")
    var teacherName : String,

    @ColumnInfo(name = "type")
    var type : String,

    @ColumnInfo(name = "links") // "zoom"::"link","tg"::"link" ...
    var links : String,

    @ColumnInfo(name = "description")
    var description: String
)