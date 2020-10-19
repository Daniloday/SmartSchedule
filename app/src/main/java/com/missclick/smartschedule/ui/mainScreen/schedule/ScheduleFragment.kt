package com.missclick.smartschedule.ui.mainScreen.schedule

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.groupie.DayItem
import com.missclick.smartschedule.adapters.groupie.LessonEmptyItem
import com.missclick.smartschedule.adapters.groupie.LessonItem
import com.missclick.smartschedule.adapters.tree.DayNodeBinder
import com.missclick.smartschedule.adapters.tree.EmptyLessonsNodeBinder
import com.missclick.smartschedule.adapters.tree.LessonToScheduleNodeBinder
import com.missclick.smartschedule.adapters.tree.LessonsNodeBinder
import com.missclick.smartschedule.data.models.AddLessonToScheduleModel
import com.missclick.smartschedule.data.models.LessonInSchedule
import com.missclick.smartschedule.data.models.ScheduleModel
import com.missclick.smartschedule.ui.lessons.LessonsFragment
import com.missclick.smartschedule.viewstates.ScheduleViewStates
import com.xwray.groupie.*
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.coroutines.flow.callbackFlow
import tellh.com.recyclertreeview_lib.LayoutItemType
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewAdapter
import java.util.*
import kotlin.text.Typography.section


class ScheduleFragment : Fragment() {

    private var paramStart: String? = null
    private lateinit var scheduleViewModel: ScheduleViewModel
    private lateinit var groupAdapter : GroupAdapter<GroupieViewHolder>
    private var data = mutableListOf<Section>()
    private  var expData = mutableListOf<Boolean>()
    private var expDataGroup = mutableListOf<ExpandableGroup>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramStart = it.getString("from")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel =
            ViewModelProvider(requireActivity()).get(ScheduleViewModel::class.java)
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleInit()
        (activity as MainActivity).toolbar_edit.setOnClickListener {
            scheduleViewModel.editSchedule()
        }

        (activity as MainActivity).toolbar_save.setOnClickListener {
            scheduleViewModel.saveSchedule()
        }

        scheduleViewModel.stateData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                //TODO (optional) dobavit updateState, менять адаптер через notifySetDataChanged()
                is ScheduleViewStates.LoadingState -> {
//                    recycler_schedule.visibility = View.GONE
                    (activity as MainActivity).toolbar_edit.visibility = View.GONE
                    (activity as MainActivity).toolbar_save.visibility = View.GONE
//                    progress_bar_schedule.visibility = View.VISIBLE
                    scheduleViewModel.initData(state.edit)
                    Log.e("state","loading")
                }
                is ScheduleViewStates.EditingState -> {
                    progress_bar_schedule.visibility = View.GONE
                    recycler_schedule.visibility = View.VISIBLE
                    (activity as MainActivity).toolbar_save.visibility = View.VISIBLE
                    recyclerUpdate(state.data)
                }
                is ScheduleViewStates.LoadedState -> {
                    Log.e("state", "loaded")
                    progress_bar_schedule.visibility = View.GONE
                    recycler_schedule.visibility = View.VISIBLE
                    (activity as MainActivity).toolbar_edit.visibility = View.VISIBLE
                    recyclerUpdate(state.data)
                }
                is ScheduleViewStates.ErrorState -> {
                    //TODO exception (nado sdelat try catch dlya raboti s bd)
                }
            }
        })
    }


    override fun onResume() {
        super.onResume()
        scheduleViewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        expData = mutableListOf()
        for(state in expDataGroup){
            expData.add(state.isExpanded)
        }
    }

    private fun recycleInit() {
        Log.e("recycle", "init")
        data = mutableListOf()
        expDataGroup = mutableListOf<ExpandableGroup>()
        groupAdapter = GroupAdapter()
        for (i in 0..4){
            val dayName =  resources.getStringArray(R.array.week_days)[i]
            val exp = ExpandableGroup(DayItem(dayName)).apply {
                val section = Section()
                add(section)
                data.add(section)
                groupAdapter.add(this)
            }
            expDataGroup.add(exp)
        }
        recycler_schedule.apply {
            layoutManager = LinearLayoutManager(activity as MainActivity)
            adapter = groupAdapter
        }
        if (expData.size != 0 ){
            for (i in 0..4){
                expDataGroup[i].isExpanded = expData[i]
            }
        }
    }

    private fun recyclerUpdate(newData : List<List<Item>>){
        Log.e("recycle","update")
        for (i in 0..4){
            data[i].update(newData[i])
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString("from", param)
                }
            }
    }
}