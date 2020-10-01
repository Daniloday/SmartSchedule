package com.missclick.smartschedule.ui.lessons

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.LessonAdapter
import kotlinx.android.synthetic.main.lessons_fragment.*
import kotlinx.coroutines.GlobalScope

class LessonsFragment : Fragment() {

    companion object {
        fun newInstance() = LessonsFragment()
    }

    private lateinit var viewModel: LessonsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lessons_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_add_lesson.setOnClickListener{
            it.findNavController().navigate(R.id.addLessonFragment)
        }
        viewModel.getLessons()
        viewModel.lessonsLiveData.observe(viewLifecycleOwner, Observer {
            recycle_lessons.adapter = LessonAdapter(it)
            recycle_lessons.layoutManager = LinearLayoutManager(activity as MainActivity)
        })
    }

}