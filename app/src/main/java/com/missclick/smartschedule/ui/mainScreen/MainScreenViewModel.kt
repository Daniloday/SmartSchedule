package com.missclick.smartschedule.ui.mainScreen

import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.repository.ILessonRepository
import javax.inject.Inject

class MainScreenViewModel : ViewModel() {

    @Inject lateinit var repository: ILessonRepository

    init {
        App.appComponent.inject(this)
    }

//    val state = MutableLiveData<MainViewStates>().default(initialValue = MainViewStates.LoadedState(Schedule()))

}