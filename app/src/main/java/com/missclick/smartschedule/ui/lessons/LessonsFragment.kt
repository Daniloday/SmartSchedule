package com.missclick.smartschedule.ui.lessons

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.missclick.smartschedule.R
import kotlinx.android.synthetic.main.lessons_fragment.*

class LessonsFragment : Fragment() {

    companion object {
        fun newInstance() = LessonsFragment()
    }

    private lateinit var viewModel: LessonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lessons_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_add_lesson.setOnClickListener{
            it.findNavController().navigate(R.id.addLessonFragment)
        }
    }

}