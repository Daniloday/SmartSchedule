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
import kotlinx.android.synthetic.main.edit_lesson_fragment_reborn.*

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
        return inflater.inflate(R.layout.edit_lesson_fragment_reborn, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_lesson_name_edit_lesson.setText(lesson!!.lessonName)
        edit_lesson_teacher_edit_lesson.setText(lesson!!.teacherName)
//        spinner_lesson_types_edit_lesson.todo spinner
        edit_telegram_edit_lesson.setText(lesson!!.links["telegram"])
        edit_zoom_edit_lesson.setText(lesson!!.links["zoom"])
        edit_phone_edit_lesson.setText(lesson!!.links["phone"])
        edit_email_edit_lesson.setText(lesson!!.links["email"])
        //edit_lesson_description_edit_lesson.setText(lesson!!.description)

        button_save_lesson_edit_lesson.setOnClickListener {
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).
            hideSoftInputFromWindow(view.windowToken, 0)
            lesson!!.lessonName = edit_lesson_name_edit_lesson.text.toString()
            lesson!!.teacherName = edit_lesson_teacher_edit_lesson.text.toString()
            //todo spinner type
            lesson!!.links["telegram"] = edit_telegram_edit_lesson.text.toString()
            lesson!!.links["zoom"] = edit_zoom_edit_lesson.text.toString()
            lesson!!.links["phone"] = edit_phone_edit_lesson.text.toString()
            lesson!!.links["email"] = edit_email_edit_lesson.text.toString()
            //lesson!!.description = edit_lesson_description_edit_lesson.text.toString()
            viewModel.editLesson(lesson = lesson!!)
            it.findNavController().popBackStack()
        }
    }

    companion object {
        fun newInstance(lesson : LessonModel):Bundle{
            return Bundle().apply {
                putSerializable("lesson", lesson)
            }
        }
    }

}

