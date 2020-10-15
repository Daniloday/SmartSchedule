package com.missclick.smartschedule.ui.mainScreen.schedule

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
import com.missclick.smartschedule.data.models.LessonInSchedule
import com.missclick.smartschedule.data.models.ScheduleModel
import com.missclick.smartschedule.ui.lessons.LessonsFragment
import com.missclick.smartschedule.ui.lessons.info.LessonInfoFragment
import com.missclick.smartschedule.viewstates.ScheduleViewStates
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import tellh.com.recyclertreeview_lib.LayoutItemType
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewAdapter
import java.util.*


class ScheduleFragment : Fragment() {

    private var paramStart: String? = null
    private lateinit var scheduleViewModel: ScheduleViewModel
    var LM : Parcelable? = null
    var adapter : TreeViewAdapter? = null
    var data : ArrayList<TreeNode<ScheduleModel>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("fragment", "schedule")
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
//            refresh()
            scheduleViewModel.editSchedule()
        }

        (activity as MainActivity).toolbar_save.setOnClickListener {
            scheduleViewModel.saveSchedule()
        }

        scheduleViewModel.stateData.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                //TODO (optional) dobavit updateState, менять адаптер через notifySetDataChanged()
                is ScheduleViewStates.LoadingState -> {
//                    recycler_schedule.visibility = View.GONE
                    (activity as MainActivity).toolbar_edit.visibility = View.GONE
                    (activity as MainActivity).toolbar_save.visibility = View.GONE
                    progress_bar_schedule.visibility = View.VISIBLE
                    scheduleViewModel.initData(state.edit)
                }
                is ScheduleViewStates.EditingState -> {
                    progress_bar_schedule.visibility = View.GONE
                    recycler_schedule.visibility = View.VISIBLE
                    (activity as MainActivity).toolbar_save.visibility = View.VISIBLE
//                    data = state.data
                    refresh()

                }
                is ScheduleViewStates.LoadedState -> {
                    Log.e("state", "loaded")
                    Log.e("adapter", state.data.toString())
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


    fun refresh(){
        Log.e("refresh",adapter.toString())
//        adapter?.refresh(data as List<TreeNode<LayoutItemType>>?)
//        adapter?.expand()
//        data?.get(2)?.d
//        adapter?.

//        this.data?.get(1)?.collapse()
    }

    private fun configRecyclerData(data : ArrayList<TreeNode<ScheduleModel>>){
        this.data = data
        recycler_schedule.layoutManager = LinearLayoutManager(activity as MainActivity)
        adapter = TreeViewAdapter(data as List<TreeNode<LayoutItemType>>?,
            listOf(DayNodeBinder(),
                EmptyLessonsNodeBinder(),
                LessonsNodeBinder(
                    object : LessonsNodeBinder.Callback {
                        override fun onItemClicked(item: LessonInSchedule) {
                            scheduleViewModel.onPause(2)
                            view?.findNavController()?.navigate(
                                R.id.lessonInfoFragment,
                                LessonInfoFragment.newInstance(param = item.lessonModel)
                            )
                        }
                    }
                ),
                LessonToScheduleNodeBinder(
                    object : LessonToScheduleNodeBinder.Callback {
                        override fun onItemClicked(item: AddLessonToScheduleModel) {
                            scheduleViewModel.onPause(1)
                            view?.findNavController()?.navigate(
                                R.id.nav_lessons,
                                LessonsFragment.newInstance(day = item.day, couple = item.couple
                                )
                            )
                        }
                    })
            )
        )

        adapter!!.setOnTreeNodeListener(object : TreeViewAdapter.OnTreeNodeListener{
            override fun onClick(node: TreeNode<*>?, holder: RecyclerView.ViewHolder?): Boolean {
                if (!node?.isLeaf!!) {
                    //Update and toggle the node.
                    onToggle(!node.isExpand, holder);
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
        recycler_schedule.adapter = adapter


//        recycler_schedule.setAddStatesFromChildren(true)
        if(LM != null ) (recycler_schedule.layoutManager as LinearLayoutManager).onRestoreInstanceState(LM)

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