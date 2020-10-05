package com.missclick.smartschedule.ui.lessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LessonsViewModel : ViewModel() {
    @Inject
    lateinit var repository: ILessonRepository

    //var lessons : List<LessonModel>? = null
    var lessonsLiveData = MutableLiveData<List<LessonModel>>()


    init {
        App.appComponent.inject(this)
        //lessonsLiveData.value = lessons
    }

     fun getLessons(){
        GlobalScope.launch(Dispatchers.IO) {
            Thread.sleep(1000)
//            var lessons = listOf(LessonModel(0,
//                "kek",
//                "anime",
//                mapOf("zoom" to "link"),
//                "mem"
//            ))
            var lessons = repository.getLessons()
            withContext(Dispatchers.Main){
                lessonsLiveData.value = lessons
            }
        }
    }
}