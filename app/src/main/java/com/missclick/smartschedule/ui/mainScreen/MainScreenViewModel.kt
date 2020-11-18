package com.missclick.smartschedule.ui.mainScreen

import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.models.SettingsModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import javax.inject.Inject

class MainScreenViewModel : ViewModel() {

    @Inject lateinit var repository: ILessonRepository
    var settings : SettingsModel? = null
    init {
        App.appComponent.inject(this)
    }

    suspend fun getSettings(){
        settings = repository.getSettings()
    }

}