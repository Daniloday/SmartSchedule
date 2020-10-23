package com.missclick.smartschedule.viewstates

import com.missclick.smartschedule.data.models.Schedule
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import tellh.com.recyclertreeview_lib.TreeNode
import java.lang.Exception

sealed class ScheduleViewStates {
    //object NoDataState : ScheduleViewStates()
    class LoadingState(var edit : Boolean = false) : ScheduleViewStates()
    class EditingState(var data1: List<List<Item>>, var data2: List<List<Item>>) : ScheduleViewStates()
    class LoadedState(var data1 : List<List<Item>>, var data2: List<List<Item>>) : ScheduleViewStates()
    class ErrorState(e : Exception) : ScheduleViewStates()
}

//sealed class RedactorState {
//    object Editing : RedactorState()
//    object Saving : RedactorState()
//}