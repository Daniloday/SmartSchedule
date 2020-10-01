package com.missclick.smartschedule.ui.lessons.addLesson

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.missclick.smartschedule.R

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

}