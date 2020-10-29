package com.missclick.smartschedule.ui.lessons.addLesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.ShortLessonModel
import com.missclick.smartschedule.ui.lessons.info.LessonInfoFragment
import kotlinx.android.synthetic.main.fragment_add_lesson_main.*


class AddMain : Fragment() {
    // TODO: Rename and change types of parameters

    var toLinks = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        enterTransition = forward

        val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        returnTransition = backward
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_lesson_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_lesson_next_to_links.setOnClickListener{
            val shortLesson = ShortLessonModel(
                lessonName = edit_lesson_name_add_lesson.text.toString(),
                teacherName = edit_lesson_teacher_add_lesson.text.toString(),
                type = spinner_lesson_types_add_lesson.selectedItem.toString()
            )
            toLinks = true
            it.findNavController().navigate(R.id.addLinks, AddLinks.newInstance(shortLesson))
        }
    }


}