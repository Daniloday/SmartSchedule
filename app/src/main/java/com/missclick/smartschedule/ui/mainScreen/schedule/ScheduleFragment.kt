package com.missclick.smartschedule.ui.mainScreen.schedule

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.groupie.DayItem
import com.missclick.smartschedule.viewstates.ScheduleViewStates
import com.xwray.groupie.*
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.schedule_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel = 
            ViewModelProvider(requireActivity()).get(ScheduleViewModel()::class.java)
        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleViewModel.mainActivity = activity as MainActivity
        GlobalScope.launch(Dispatchers.IO) {
            scheduleViewModel.getSettings()
            withContext(Dispatchers.Main){
                recycleInit()
            }
        }

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
                    (activity as MainActivity).toolbar_edit.visibility = View.GONE
                    (activity as MainActivity).toolbar_save.visibility = View.GONE
                    scheduleViewModel.initData(state.edit)
                }
                is ScheduleViewStates.EditingState -> {
                    (activity as MainActivity).toolbar_save.visibility = View.VISIBLE
                    if (week == 1) recyclerUpdate(state.data1)
                    else recyclerUpdate(state.data2)
                }
                is ScheduleViewStates.LoadedState -> {
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
        expDataGroup = mutableListOf()
        groupAdapter = GroupAdapter()

        for (i in 0 until (scheduleViewModel.settings?.days?.size!!)){
//            val dayName =  resources.getStringArray(R.array.week_days)[i]
            val dayName = scheduleViewModel.settings!!.days[i]
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
            for (i in 0 until (scheduleViewModel.settings?.days?.size!!)){
                expDataGroup[i].isExpanded = expData[i]
            }
        }
    }

    private fun recyclerUpdate(newData : List<List<Item>>){
        for (i in 0 until (scheduleViewModel.settings?.days?.size!!)){
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