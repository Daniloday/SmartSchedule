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

class ScheduleViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    var edit: Boolean = false
    var onPause: Boolean = false

    var nodesLiveData = MutableLiveData<ArrayList<TreeNode<ScheduleModel>>>()
    var editStateLiveData = MutableLiveData<Boolean>()
    val stateData = MutableLiveData<ScheduleViewStates>().default(initialValue = ScheduleViewStates.LoadingState)
    //val stateRedactor = MutableLiveData<RedactorState>().default(initialValue = RedactorState.Saving)

    init {
        App.appComponent.inject(this)
        //lessonsLiveData.value = lessons
    }

    fun editSchedule(){
        edit = true
        stateData.value = ScheduleViewStates.LoadingState
    }

    fun onResume(){
        if(onPause) editSchedule()
    }

    fun saveSchedule(){
        edit = false
        stateData.value = ScheduleViewStates.LoadingState
    }

    fun initData() {
        GlobalScope.launch(Dispatchers.IO) {
            val nodes = ArrayList<TreeNode<ScheduleModel>>()
            initAllDays(nodes = nodes)//, edit = edit)
            withContext(Dispatchers.Main){
                if (edit) stateData.value = ScheduleViewStates.EditingState(nodes)
                else stateData.value = ScheduleViewStates.LoadedState(nodes)
            }
        }
    }

    private suspend fun initAllDays(nodes : ArrayList<TreeNode<ScheduleModel>>){//, edit: Boolean){
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