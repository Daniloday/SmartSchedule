package com.missclick.smartschedule.ui.mainScreen.schedule

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.groupie.DayItem
import com.missclick.smartschedule.ui.lessons.LessonsViewModel
import com.missclick.smartschedule.viewstates.ScheduleViewStates
import com.xwray.groupie.*
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.schedule_lesson_in_schedule.*


class ScheduleFragment : Fragment() {

    private var week: Int? = null
    private lateinit var scheduleViewModel: ScheduleViewModel
    private lateinit var groupAdapter : GroupAdapter<GroupieViewHolder>
    private var data = mutableListOf<Section>()
    private  var expData = mutableListOf<Boolean>()
    private var expDataGroup = mutableListOf<ExpandableGroup>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            week = it.getInt("week")
            Log.e("bundle", week.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel = //ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
            ViewModelProvider(requireActivity()).get(ScheduleViewModel()::class.java)
//        scheduleViewModel.week = week!!
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
                    Log.e("LoadingStateFragment", state.edit.toString())
                    Log.e("state","loading")
                }
                is ScheduleViewStates.EditingState -> {
                    progress_bar_schedule.visibility = View.GONE
                    recycler_schedule.visibility = View.VISIBLE
                    (activity as MainActivity).toolbar_save.visibility = View.VISIBLE
                    if (week == 1) recyclerUpdate(state.data1)
                    else recyclerUpdate(state.data2)
                }
                is ScheduleViewStates.LoadedState -> {
                    Log.e("state", "loaded")
                    progress_bar_schedule.visibility = View.GONE
                    recycler_schedule.visibility = View.VISIBLE
                    (activity as MainActivity).toolbar_edit.visibility = View.VISIBLE
                    if (week == 1) recyclerUpdate(state.data1)
                    else recyclerUpdate(state.data2)
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
        for (i in 0..4){
            data[i].update(newData[i])
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(week: Int) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putInt("week", week)
                }
            }
    }
}