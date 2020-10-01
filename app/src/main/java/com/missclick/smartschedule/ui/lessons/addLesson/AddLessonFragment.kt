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
        viewModel = ViewModelProviders.of(this).get(AddLessonViewModel::class.java)
        // TODO: Use the ViewModel
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var spinnerChoose = spinner_link.selectedItem
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

        button_add_lesson.setOnClickListener {
            if (edit_choose.text != " ")
                edit_choose.text = "${edit_choose.text}, $spinnerChoose: ${edit_link.text}"
            else
                edit_choose.text = "$spinnerChoose: ${edit_link.text}"
        }

        button_save_lesson.setOnClickListener {

        }

    }



}