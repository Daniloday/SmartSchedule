package com.missclick.smartschedule.viewstates

import com.missclick.smartschedule.data.models.Schedule
import java.lang.Exception

sealed class MainViewStates {
    object NoDataState : MainViewStates()
    object LoadingState : MainViewStates()
    class LoadedState(schedule : Schedule) : MainViewStates()
    class ErrorState(e : Exception) : MainViewStates()
}