package com.missclick.smartschedule.ui.lessons.addLesson

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddLessonViewModel::class.java)
        var spinnerChoose = spinner_link.selectedItem.toString()
        spinner_link.onItemSelectedListener = (object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                spinnerChoose = spinner_link.selectedItem.toString()
                edit_link.hint = "Enter $spinnerChoose"
            }
        })

        button_add_link.setOnClickListener {
            viewModel.addLesson(old = text_choose.text.toString(), add = edit_link.text.toString(),
            spinChoose = spinnerChoose)

        }

        viewModel.links.observe(viewLifecycleOwner, Observer {
            text_choose.text = it
        })

        button_save_lesson.setOnClickListener {
            viewModel.saveLesson(
                lessonName = edit_lesson_name.text.toString(),
                teacherName = edit_lesson_teacher.text.toString(),
                links = text_choose.text.toString(),
                description = edit_lesson_description.text.toString()
            )
        }

    }



}