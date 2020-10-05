package com.missclick.smartschedule.ui.lessons.addLesson

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import javax.inject.Inject

class AddLessonViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository

    init {
        App.appComponent.inject(this)
    }

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    var links : MutableLiveData<String> = _text

    fun addLesson(old: String, add : String, spinChoose : String){

        fun validator(a : String, b : String ) : Boolean{
            val list = b.split(",", ":")
            for( i in list)
                if (i == a) return true
            return false
        }

        var new = ""

        if (add != "" && !validator(a = spinChoose, b = old)){

            new = if (old != "")
                "$old,$spinChoose:$add"
            else
                "$spinChoose:$add"
        }
        links.value = new
    }

    fun saveLesson(lessonName : String, teacherName : String, links : String, description : String){
        Log.e("DeepLinks",links)
        val entity = LessonEntity(name = lessonName, teacherName = teacherName, links = links, description = description)
        repository.insertLesson(entity)
    }
}