package com.missclick.smartschedule.ui.lessons.addLesson

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ShortLessonModel
import kotlinx.android.synthetic.main.add_lesson_fragment.*
import kotlinx.android.synthetic.main.fragment_add_links.*
import kotlinx.android.synthetic.main.fragment_add_links.edit_email_add_lesson
import kotlinx.android.synthetic.main.fragment_add_links.edit_phone_add_lesson
import kotlinx.android.synthetic.main.fragment_add_links.edit_telegram_add_lesson
import kotlinx.android.synthetic.main.fragment_add_links.edit_zoom_add_lesson

// TODO: Rename parameter arguments, choose names that match


class AddLinks : Fragment() {

    private var lesson: ShortLessonModel? = null
    private lateinit var viewModel: AddLessonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lesson = it.getSerializable("lesson") as ShortLessonModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_links, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddLessonViewModel::class.java)
        button_lesson_next.setOnClickListener {
            val links = mutableMapOf(
                "telegram" to edit_telegram_add_lesson.text.toString(),
                "zoom" to edit_zoom_add_lesson.text.toString(),
                "phone" to edit_phone_add_lesson.text.toString(),
                "email" to edit_email_add_lesson.text.toString()
            )
            lesson?.let { lesson ->
                viewModel.saveLesson(
                    lessonName = lesson.lessonName,
                    teacherName = lesson.teacherName,
                    type = lesson.type,
                    links = links,
                    description = ""
                )
            }
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
            it.findNavController().popBackStack(R.id.nav_lessons, false)
        }
    }

    companion object {
        fun newInstance(lesson : ShortLessonModel):Bundle{
            return Bundle().apply {
                putSerializable("lesson", lesson)
            }
        }
    }
}