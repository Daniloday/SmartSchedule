package com.missclick.smartschedule.data.datasource.local.dao

import androidx.room.*
import com.missclick.smartschedule.data.datasource.local.entity.SettingsEntity

@Dao
interface SettingsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSettings(settingsEntity: SettingsEntity)

    @Query("SELECT * FROM settings")
    fun getAllSettings() : List<SettingsEntity>
}