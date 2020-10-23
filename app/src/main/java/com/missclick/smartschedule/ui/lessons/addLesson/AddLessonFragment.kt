package com.missclick.smartschedule.ui.lessons.addLesson

import android.annotation.SuppressLint
import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import kotlinx.android.synthetic.main.fragment_add_lesson.*



class AddLessonFragment : Fragment() {

    companion object {
        fun newInstance() = AddLessonFragment()
    }

    private lateinit var viewModel: AddLessonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_lesson, container, false)
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



        button_save_lesson.setOnClickListener {
            viewModel.saveLesson(
                lessonName = edit_lesson_name.text.toString(),
                teacherName = edit_lesson_teacher.text.toString(),
                type = spinner_lesson_types.selectedItem.toString(),
                links = edit_telegram_add_lesson.toString(),
                description = edit_lesson_description.text.toString()
            )
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
            (activity as MainActivity).onBackPressed()

        }

    }



}