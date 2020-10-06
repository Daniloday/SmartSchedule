package com.missclick.smartschedule.ui.mainScreen.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.data.models.AddLessonToScheduleModel
import com.missclick.smartschedule.data.models.ScheduleModel
import tellh.com.recyclertreeview_lib.TreeNode

class ScheduleViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is fine"
    }
    val text: LiveData<String> = _text

    fun initData(): ArrayList<TreeNode<ScheduleModel>> {
        val nodes = ArrayList<TreeNode<ScheduleModel>>()
        initAllDays(nodes)
        addChildToDay(nodes[0], "Monday")
//        val monday = TreeNode<ScheduleModel>(ScheduleModel("Monday"))
//        nodes.add(monday)
//        monday.addChild(TreeNode(ScheduleModel("ded")))
//        monday.addChild(TreeNode(ScheduleModel("kek")))
//        val thu = TreeNode<ScheduleModel>(ScheduleModel("Thursday"))
//        nodes.add(thu)
//        thu.addChild(TreeNode(ScheduleModel("kirienko")))
        return nodes
    }

    fun initAllDays(nodes : ArrayList<TreeNode<ScheduleModel>>){
        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        for(day in days)
            nodes.add(TreeNode(ScheduleModel(day)))
    }

    fun addChildToDay(node : TreeNode<ScheduleModel>, day : String){
        val dayList = listOf<ScheduleModel>()
        if(dayList.isEmpty()){
            node.addChild(TreeNode(AddLessonToScheduleModel(day)))
        }
    }
}