package com.missclick.smartschedule.ui.lessons.addLesson

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddLessonViewModel : ViewModel() {

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
                "$old, \n $spinChoose: $add"
            else
                "$spinChoose: $add"
        }
        links.value = new
    }
}