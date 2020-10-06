package com.missclick.smartschedule.ui.lessons.edit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.missclick.smartschedule.R

class EditLessonFragment : Fragment() {

    companion object {
        fun newInstance() = EditLessonFragment()
    }

    private lateinit var viewModel: EditLessonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_lesson_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditLessonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}