package com.missclick.smartschedule.ui.mainScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.data.models.Schedule
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.extensions.default
import com.missclick.smartschedule.viewstates.MainViewStates

class MainScreenViewModel(repository: ILessonRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    val state = MutableLiveData<MainViewStates>().default(initialValue = MainViewStates.LoadedState(Schedule()))
}