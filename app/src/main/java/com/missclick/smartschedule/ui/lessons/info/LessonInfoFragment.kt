package com.missclick.smartschedule.ui.lessons.info

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.ui.mainScreen.schedule.ScheduleFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.lesson_info_fragment.*
import kotlinx.android.synthetic.main.list_lesson_item.*
import java.io.Serializable

class LessonInfoFragment : Fragment() {

    companion object {
        fun newInstance(param : LessonModel):Bundle{
//            LessonInfoFragment().apply {
//                arguments = Bundle().apply {
//                    putSerializable("from", param)
//                }
//            }
            return Bundle().apply {
                putSerializable("from", param)
            }
        }

    }

    private lateinit var viewModel: LessonInfoViewModel
    private var paramStart: LessonModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramStart = it.getSerializable("from") as LessonModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lesson_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar_edit.visibility = View.VISIBLE
        text_lesson_name.text = paramStart!!.lessonName
        text_teacher_name.text = paramStart!!.teacherName
        text_description.text = paramStart!!.description

        (activity as MainActivity).toolbar_edit.setOnClickListener {
            view.findNavController().navigate(R.id.editFragment)
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).toolbar_edit.visibility = View.GONE
    }

}