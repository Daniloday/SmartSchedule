package com.missclick.smartschedule.ui.lessons

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialSharedAxis
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.LessonAdapter
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.ui.lessons.info.LessonInfoFragment
import kotlinx.android.synthetic.main.lessons_fragment.*
import kotlinx.coroutines.GlobalScope
import java.io.Serializable

class LessonsFragment : Fragment() {

    private lateinit var viewModel: LessonsViewModel
    var day : String? = null
    var couple : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonsViewModel::class.java)
        arguments?.let {
            day = it.getString("day")
            couple = it.getInt("couple")
        }
        val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        reenterTransition = backward

        val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        exitTransition = forward
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
            recycle_lessons.adapter = LessonAdapter(it,
                object : LessonAdapter.Callback {
                    override fun onItemClicked(item: LessonModel) {
                        Log.e("Callback","works!")
                        if(day == null){
                            LessonInfoFragment.newInstance(param = item)
                            view.findNavController().navigate(R.id.lessonInfoFragment, LessonInfoFragment.newInstance(item))
                        }
                        else{
                            //Тогда этот фрагмент вызвался с расписания и надо внести в бд предмет и вернуться
                            viewModel.addLessonToSchedule(day = day!!, couple = couple!!, lessonModel = item)
                            view.findNavController().popBackStack()
                        }
                    }
                }
            )
            recycle_lessons.layoutManager = LinearLayoutManager(activity as MainActivity)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLessons()
        Log.e("OnResume","works!")
    }

    companion object {
        fun newInstance(day : String, couple : Int) : Bundle {
            return Bundle().apply {
                putString("day", day)
                putInt("couple", couple)
            }
        }
    }

}