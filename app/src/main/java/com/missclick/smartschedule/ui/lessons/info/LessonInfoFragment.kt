package com.missclick.smartschedule.ui.lessons.info

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.ui.lessons.edit.EditLessonFragment
import kotlinx.android.synthetic.main.app_bar_main.*

import kotlinx.android.synthetic.main.lesson_info_fragment_reborn.*

class LessonInfoFragment : Fragment() {

    private lateinit var viewModel: LessonInfoViewModel
    private var lesson: LessonModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lesson = it.getSerializable("lesson") as LessonModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lesson_info_fragment_reborn, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).toolbar_edit.visibility = View.VISIBLE
        text_lesson_name_info_lesson.text = lesson!!.lessonName
        text_lesson_teacher_info_lesson.text = lesson!!.teacherName
        text_lesson_types_info_lesson.text = lesson!!.type
        //text_lesson_description_info_lesson.text = lesson!!.description
        if(lesson!!.links["telegram"] != ""){
            val tg = "http://t.me/" + lesson!!.links["telegram"]
            button_telegram_info_lesson.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tg))
                startActivity(intent)
            }
            button_telegram_info_lesson.text = "t.me/" + lesson!!.links["telegram"]
            button_telegram_info_lesson_top.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tg))
                startActivity(intent)
            }
        } else button_telegram_info_lesson.visibility = View.GONE

        if(lesson!!.links["phone"] != ""){
            val phone = "tel:" + lesson!!.links["phone"]
            button_phone_info_lesson_top.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phone))
                startActivity(intent)
            }
            button_phone_info_lesson.text = lesson!!.links["phone"]
            button_phone_info_lesson.setOnClickListener{
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phone))
                startActivity(intent)
            }
        } else button_phone_info_lesson.visibility = View.GONE

        if(lesson!!.links["zoom"] != ""){
            val zoom = lesson!!.links["zoom"]
            button_zoom_info_lesson.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(zoom))
                startActivity(intent)
            }
            button_zoom_info_lesson.text = lesson!!.links["zoom"]
        } else button_zoom_info_lesson.visibility = View.GONE

        if(lesson!!.links["email"] != ""){
            val email = "mailto:" + lesson!!.links["email"]
            button_email_info_lesson.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(email))
                startActivity(intent)
            }
            button_email_info_lesson.text = lesson!!.links["email"]
        } else button_email_info_lesson.visibility = View.GONE




        (activity as MainActivity).toolbar_edit.setOnClickListener {
            view.findNavController().navigate(R.id.edit_fragment,EditLessonFragment.newInstance(
                lesson = lesson!!
            ))
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).toolbar_edit.visibility = View.GONE
    }

    companion object {
        fun newInstance(lesson : LessonModel):Bundle{
            return Bundle().apply {
                putSerializable("lesson", lesson)
            }
        }
    }

}