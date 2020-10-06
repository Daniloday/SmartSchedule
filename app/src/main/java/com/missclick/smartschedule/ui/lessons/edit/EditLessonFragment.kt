package com.missclick.smartschedule.ui.lessons.edit

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.findNavController
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel
import kotlinx.android.synthetic.main.edit_lesson_fragment.*
import kotlinx.android.synthetic.main.lesson_info_fragment.*

class EditLessonFragment : Fragment() {



    private lateinit var viewModel: EditLessonViewModel
    private var lesson: LessonModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditLessonViewModel::class.java)
        arguments?.let {
            lesson = it.getSerializable("lesson") as LessonModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_lesson_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_lesson_name.setText(lesson!!.lessonName)
        edit_teacher_name.setText(lesson!!.teacherName)
        edit_description.setText(lesson!!.description)
        button_save_edit.setOnClickListener {
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).
            hideSoftInputFromWindow(view.windowToken, 0)
            lesson!!.lessonName = edit_lesson_name.text.toString()
            lesson!!.teacherName = edit_teacher_name.text.toString()
            lesson!!.description = edit_description.text.toString()
            viewModel.editLesson(lesson = lesson!!)
            it.findNavController().navigate(R.id.nav_lessons)
        }
    }

    companion object {
        fun newInstance(param : LessonModel):Bundle{
            return Bundle().apply {
                putSerializable("lesson", param)
            }
        }
    }

}

