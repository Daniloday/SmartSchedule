package com.missclick.smartschedule.viewstates

import com.xwray.groupie.kotlinandroidextensions.Item
import java.lang.Exception

sealed class ExportViewStates {
    object LoadingState : ExportViewStates()
    class LoadedState(var id : String) : ExportViewStates()
    class ErrorState(var e : String) : ExportViewStates()
}