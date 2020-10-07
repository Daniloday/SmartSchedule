package com.missclick.smartschedule.viewstates

import com.missclick.smartschedule.data.models.Schedule
import com.missclick.smartschedule.data.models.ScheduleModel
import tellh.com.recyclertreeview_lib.TreeNode
import java.lang.Exception

sealed class ScheduleViewStates {
    //object NoDataState : ScheduleViewStates()
    object LoadingState : ScheduleViewStates()
    class EditingState(var data: ArrayList<TreeNode<ScheduleModel>>) : ScheduleViewStates()
    class LoadedState(var data : ArrayList<TreeNode<ScheduleModel>>) : ScheduleViewStates()
    class ErrorState(e : Exception) : ScheduleViewStates()
}

//sealed class RedactorState {
//    object Editing : RedactorState()
//    object Saving : RedactorState()
//}