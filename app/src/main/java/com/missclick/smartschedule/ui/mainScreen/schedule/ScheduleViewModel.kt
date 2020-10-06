package com.missclick.smartschedule.ui.mainScreen.schedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.models.AddLessonToScheduleModel
import com.missclick.smartschedule.data.models.LessonInSchedule
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ScheduleModel
import com.missclick.smartschedule.data.repository.ILessonRepository
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

    var nodesLiveData = MutableLiveData<ArrayList<TreeNode<ScheduleModel>>>()

    init {
        App.appComponent.inject(this)
        //lessonsLiveData.value = lessons
    }

    fun initData() {
        GlobalScope.launch(Dispatchers.IO) {
            val nodes = ArrayList<TreeNode<ScheduleModel>>()
            initAllDays(nodes = nodes)
            withContext(Dispatchers.Main){
                nodesLiveData.value = nodes
            }
        }
    }

    private suspend fun initAllDays(nodes : ArrayList<TreeNode<ScheduleModel>>){
        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val daysEntity = repository.getAllDays()

        for(day in days) {
            val node = TreeNode(ScheduleModel(day))
            addChildToDay(node = node,
                day = day,
                lessons = getLessonsInDay(days = daysEntity, day = day))
            nodes.add(node)
        }
    }

    private fun getLessonsInDay(days : List<DayEntity>, day: String) : List<Int>{
        val lessons = mutableListOf<Int>()
        for (dayEntity in days){
            if(dayEntity.dayName == day)
                lessons.add(dayEntity.lessonId)
        }
        return lessons
    }

    private suspend fun addChildToDay(node : TreeNode<ScheduleModel>, day : String, lessons : List<Int>){
        val dayList = listOf<ScheduleModel>()
        for(lessonId in lessons)
            node.addChild(TreeNode(LessonInSchedule(repository.getLessonById(lessonId))))
        if(dayList.isEmpty()) {
            Log.e("ScheduleViewModel", "empty")
            node.addChild(TreeNode(AddLessonToScheduleModel(day)))
        }

    }
}

//        val monday = TreeNode<ScheduleModel>(ScheduleModel("Monday"))
//        nodes.add(monday)
//        monday.addChild(TreeNode(ScheduleModel("ded")))
//        monday.addChild(TreeNode(ScheduleModel("kek")))
//        val thu = TreeNode<ScheduleModel>(ScheduleModel("Thursday"))
//        nodes.add(thu)
//        thu.addChild(TreeNode(ScheduleModel("kirienko")))