package com.missclick.smartschedule.ui.mainScreen.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.LessonsNodeBinder
import com.missclick.smartschedule.data.models.ScheduleModel
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
            ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_schedule, container, false)
        //val textView: TextView = root.findViewById(R.id.text_gallery)
//        scheduleViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = paramStart
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = scheduleViewModel.initData()
        configRecyclerView(data)
    }

    fun configRecyclerView(data : ArrayList<TreeNode<ScheduleModel>>){

        recycler_schedule.layoutManager = LinearLayoutManager(activity as MainActivity)
        val adapter = TreeViewAdapter(data as List<TreeNode<LayoutItemType>>?,
            Arrays.asList(LessonsNodeBinder()))
        //Log.e("data", (data as List<TreeNode<LayoutItemType>>?).toString())
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
        //Log.e("kek", "adapter")
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