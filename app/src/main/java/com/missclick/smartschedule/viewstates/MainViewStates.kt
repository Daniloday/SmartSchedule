package com.missclick.smartschedule.viewstates

import com.missclick.smartschedule.data.models.ScheduleDayModel
import java.lang.Exception

sealed class MainViewStates {
    object NoDataState : MainViewStates()
    object LoadingState : MainViewStates()
    class LoadedState(scheduleDayModel : ScheduleDayModel) : MainViewStates()
    class ErrorState(e : Exception) : MainViewStates()
}