package com.missclick.smartschedule.viewstates

import com.xwray.groupie.kotlinandroidextensions.Item
import java.lang.Exception

sealed class ScheduleViewStates {
    class LoadingState(var edit : Boolean = false) : ScheduleViewStates()
    class EditingState(var data1: List<List<Item>>, var data2: List<List<Item>>) : ScheduleViewStates()
    class LoadedState(var data1 : List<List<Item>>, var data2: List<List<Item>>) : ScheduleViewStates()
    class ErrorState(e : Exception) : ScheduleViewStates()
}
