package com.missclick.smartschedule.ui.mainScreen.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.DayNodeBinder
import com.missclick.smartschedule.adapters.EmptyLessonsNodeBinder
import com.missclick.smartschedule.adapters.LessonToScheduleNodeBinder
import com.missclick.smartschedule.adapters.LessonsNodeBinder
import com.missclick.smartschedule.data.models.AddLessonToScheduleModel
import com.missclick.smartschedule.data.models.EmptyLesson
import com.missclick.smartschedule.data.models.ScheduleModel
import com.missclick.smartschedule.ui.lessons.LessonsFragment
import com.missclick.smartschedule.ui.lessons.info.LessonInfoFragment
import com.missclick.smartschedule.viewstates.ScheduleViewStates
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import tellh.com.recyclertreeview_lib.LayoutItemType
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewAdapter
import java.util.*


class ScheduleFragment : Fragment() {

    private var paramStart: String? = null
    private lateinit var scheduleViewModel: ScheduleViewModel

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

        (activity as MainActivity).toolbar_edit.setOnClickListener {
            scheduleViewModel.editSchedule()
        }

        (activity as MainActivity).toolbar_save.setOnClickListener {
            scheduleViewModel.saveSchedule()
        }

        scheduleViewModel.stateData.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                //TODO (optional) dobavit updateState, менять адаптер через notifySetDataChanged()
                is ScheduleViewStates.LoadingState -> {
                    recycler_schedule.visibility = View.GONE
                    (activity as MainActivity).toolbar_edit.visibility = View.GONE
                    (activity as MainActivity).toolbar_save.visibility = View.GONE
                    progress_bar_schedule.visibility = View.VISIBLE
                    scheduleViewModel.initData()
                }
                is ScheduleViewStates.EditingState -> {
                    progress_bar_schedule.visibility = View.GONE
                    recycler_schedule.visibility = View.VISIBLE
                    (activity as MainActivity).toolbar_save.visibility = View.VISIBLE
                    configRecyclerData(state.data)
                }
                is ScheduleViewStates.LoadedState -> {
                    progress_bar_schedule.visibility = View.GONE
                    recycler_schedule.visibility = View.VISIBLE
                    (activity as MainActivity).toolbar_edit.visibility = View.VISIBLE
                    configRecyclerData(state.data)
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

    private fun configRecyclerData(data : ArrayList<TreeNode<ScheduleModel>>){
        recycler_schedule.layoutManager = LinearLayoutManager(activity as MainActivity)
        val adapter = TreeViewAdapter(data as List<TreeNode<LayoutItemType>>?,
            listOf(DayNodeBinder(),
                EmptyLessonsNodeBinder(),
                LessonsNodeBinder(),
                LessonToScheduleNodeBinder(
                    object : LessonToScheduleNodeBinder.Callback {
                        override fun onItemClicked(item: AddLessonToScheduleModel) {
                            scheduleViewModel.onPause = true
                            view?.findNavController()?.navigate(
                                R.id.nav_lessons,
                                LessonsFragment.newInstance(day = item.day, couple = item.couple
                                )
                            )
                        }
                    })
            )
        )
        adapter.setOnTreeNodeListener(object : TreeViewAdapter.OnTreeNodeListener{
            override fun onClick(node: TreeNode<*>?, holder: RecyclerView.ViewHolder?): Boolean {
                if (!node?.isLeaf()!!) {
                    //Update and toggle the node.
                    onToggle(!node.isExpand(), holder);
//                    if (!node.isExpand())
//                        adapter.collapseBrotherNode(node);
                }
                return false
            }

            override fun onToggle(p0: Boolean, p1: RecyclerView.ViewHolder?) {
//                val dirViewHolder: DirectoryNodeBinder.ViewHolder =
//                    holder as DirectoryNodeBinder.ViewHolder
//                val ivArrow: ImageView = dirViewHolder.getIvArrow()
//                val rotateDegree = if (isExpand) 90 else -90
//                ivArrow.animate().rotationBy(rotateDegree)
//                    .start()
            }

        })
        Log.e("SchFragment", data[0].childList[0].content.toString())
        recycler_schedule.adapter = adapter
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