package com.missclick.smartschedule.ui.lessons.addLesson

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import kotlinx.android.synthetic.main.add_lesson_fragment.*



class AddLessonFragment : Fragment() {

    private lateinit var viewModel: AddLessonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_lesson_fragment, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        enterTransition = forward

        val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        returnTransition = backward
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddLessonViewModel::class.java)

        button_save_lesson_add_lesson.setOnClickListener {
            val links = mutableMapOf(
                "telegram" to edit_telegram_add_lesson.text.toString(),
                "zoom" to edit_zoom_add_lesson.text.toString(),
                "phone" to edit_phone_add_lesson.text.toString(),
                "email" to edit_email_add_lesson.text.toString()
            )
            viewModel.saveLesson(
                lessonName = edit_lesson_name_add_lesson.text.toString(),
                teacherName = edit_lesson_teacher_add_lesson.text.toString(),
                type = spinner_lesson_types_add_lesson.selectedItem.toString(),
                links = links,
                description = edit_lesson_description_add_lesson.text.toString()
            )
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
            it.findNavController().popBackStack()

        }

    }



}