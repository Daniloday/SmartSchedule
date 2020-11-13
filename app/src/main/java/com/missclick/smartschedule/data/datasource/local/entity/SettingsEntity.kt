package com.missclick.smartschedule.data.datasource.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity (
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "settingsId")
        var id : Int? = null,

        @ColumnInfo(name = "days")
        var days : String = "Monday,Tuesday,Wednesday,Thursday,Friday",

        @ColumnInfo(name = "couples")
        var couples : Int = 4,

        @ColumnInfo(name = "weeks")
        var weeks : Int = 2

)