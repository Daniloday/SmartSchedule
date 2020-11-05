package com.missclick.smartschedule.ui.lessons

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialSharedAxis
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.LessonAdapter
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.ui.lessons.info.LessonInfoFragment
import com.missclick.smartschedule.ui.lessons.info.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.lessons_fragment.*

class LessonsFragment : Fragment() {

    private lateinit var viewModel: LessonsViewModel
    private var day : String? = null
    private var couple : Int? = null
    private var week : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonsViewModel::class.java)
        arguments?.let {
            day = it.getString("day")
            couple = it.getInt("couple")
            week = it.getInt("week")
        }
        val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false)  // wh's it?
        reenterTransition = backward

        val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        exitTransition = forward
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lessons_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar_edit.visibility = View.GONE
        (activity as MainActivity).toolbar_save.visibility = View.GONE
        swipeToRefresh.setOnRefreshListener {
            viewModel.getLessons()
        }
        button_add_lesson.setOnClickListener{
            it.findNavController().navigate(R.id.addMain)
        }
        viewModel.getLessons()
        viewModel.lessonsLiveData.observe(viewLifecycleOwner, Observer {
           // val kek = it as MutableList<LessonModel>
            recycle_lessons.adapter = LessonAdapter(
                it as MutableList<LessonModel>,
                object : LessonAdapter.Callback {
                    override fun onItemClicked(item: LessonModel) {
                        callbackLessonClicked(item)
                    }
                }
            )
            val swipeHandler = object : SwipeToDeleteCallback(activity as MainActivity){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = recycle_lessons.adapter as LessonAdapter
                    viewModel.removeLesson(it[viewHolder.adapterPosition])
                    adapter.removeAt(viewHolder.adapterPosition)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(recycle_lessons)
            recycle_lessons.layoutManager = LinearLayoutManager(activity as MainActivity)
        })
        viewModel.refreshing.observe(viewLifecycleOwner, Observer {
            if(!it) swipeToRefresh.isRefreshing = false
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLessons()
    }

    fun callbackLessonClicked(item : LessonModel){
        if(day == null){
            LessonInfoFragment.newInstance(lesson = item)
            requireView().findNavController().navigate(R.id.lesson_info_fragment, LessonInfoFragment.newInstance(item))
        }
        else{
            //Тогда этот фрагмент вызвался с расписания и надо внести в бд предмет и вернуться
            (activity as MainActivity).cancelNotification()
            viewModel.addLessonToSchedule(day = day!!, couple = couple!!, lessonModel = item, week = week!!)
            (activity as MainActivity).setNotification()
            requireView().findNavController().popBackStack()
        }
    }

    companion object {
        fun newInstance(day : String, couple : Int, week : Int) : Bundle {
            return Bundle().apply {
                putString("day", day)
                putInt("couple", couple)
                putInt("week", week)
            }
        }
    }

}