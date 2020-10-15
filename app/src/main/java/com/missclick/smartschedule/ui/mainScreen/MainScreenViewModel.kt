package com.missclick.smartschedule.ui.mainScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.datasource.local.LocalDataSource
import com.missclick.smartschedule.data.datasource.remote.RemoteDataSource
import com.missclick.smartschedule.data.models.Schedule
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.data.repository.LessonRepository
import com.missclick.smartschedule.extensions.default
import com.missclick.smartschedule.viewstates.MainViewStates
import javax.inject.Inject

class MainScreenViewModel : ViewModel() {

    @Inject lateinit var repository: ILessonRepository

    init {
        App.appComponent.inject(this)
    }

//    val state = MutableLiveData<MainViewStates>().default(initialValue = MainViewStates.LoadedState(Schedule()))

}