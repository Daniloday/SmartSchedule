package com.missclick.smartschedule.ui.mainScreen.schedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.models.*
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.extensions.default
import com.missclick.smartschedule.viewstates.MainViewStates
import com.missclick.smartschedule.viewstates.ScheduleViewStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tellh.com.recyclertreeview_lib.TreeNode
import java.util.prefs.NodeChangeEvent
import javax.inject.Inject
import kotlin.reflect.typeOf

class ScheduleViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    var onPause: Int = 0 // 0 - not onPause, 1 - in add lesson, 2 - in info
    var nodesLiveData = MutableLiveData<ArrayList<TreeNode<ScheduleModel>>>()
    var editStateLiveData = MutableLiveData<Boolean>()
    val stateData = MutableLiveData<ScheduleViewStates>().default(initialValue = ScheduleViewStates.LoadingState())

    init {
        App.appComponent.inject(this)
    }

    fun editSchedule(){
        stateData.value = ScheduleViewStates.LoadingState(true)
    }

    fun onResume(){
        if(onPause == 1) stateData.value = ScheduleViewStates.LoadingState(true)
        if(onPause == 2) stateData.value = ScheduleViewStates.LoadingState()
        onPause = 0
    }

    fun onPause(where : Int){
        onPause = where
    }

    fun saveSchedule(){
        stateData.value = ScheduleViewStates.LoadingState()
    }

    fun initData(edit : Boolean) {
        GlobalScope.launch(Dispatchers.IO) {
            val nodes = ArrayList<TreeNode<ScheduleModel>>()
            initAllDays(nodes = nodes, edit = edit)//, edit = edit)
            withContext(Dispatchers.Main){
                if (edit) stateData.value = ScheduleViewStates.EditingState(nodes)
                else stateData.value = ScheduleViewStates.LoadedState(nodes)
            }
        }
    }

    private suspend fun initAllDays(nodes : ArrayList<TreeNode<ScheduleModel>>, edit : Boolean){//, edit: Boolean){
        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val daysEntity = repository.getAllDays()
        for(day in days) {
            val weekDay = TreeNode(ScheduleModel(day))
            for(couple in 1..4){
                val lessonId = getLessonId(days = daysEntity, day = day, couple = couple)
                if (lessonId != null) weekDay.addChild(TreeNode(LessonInSchedule(repository.getLessonById(lessonId))))
                else {
                    if(edit) weekDay.addChild(TreeNode(AddLessonToScheduleModel(day = day, couple = couple)))
                        else weekDay.addChild(TreeNode(EmptyLesson()))
                }
            }
            nodes.add(weekDay)
        }
    }

    private fun getLessonId(days : List<DayEntity>, day: String, couple : Int) : Int?{
        for (dayEntity in days){
                if(dayEntity.dayName == day && dayEntity.couple == couple)
                    return dayEntity.lessonId
        }
        return null
    }
}