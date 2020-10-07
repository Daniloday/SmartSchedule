package com.missclick.smartschedule.viewstates

import com.missclick.smartschedule.data.models.Schedule
import java.lang.Exception

sealed class ScheduleViewStates {
    object NoDataState : ScheduleViewStates()
    object LoadingState : ScheduleViewStates()
    class LoadedState(schedule : Schedule) : ScheduleViewStates()
    class ErrorState(e : Exception) : ScheduleViewStates()
}