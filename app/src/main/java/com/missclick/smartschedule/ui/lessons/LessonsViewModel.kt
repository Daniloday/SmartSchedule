package com.missclick.smartschedule.ui.lessons

import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LessonsViewModel : ViewModel() {
    @Inject
    lateinit var repository: ILessonRepository

    var lessons : List<LessonModel>? = null

    init {
        App.appComponent.inject(this)
    }

    suspend fun getLessons(){
        GlobalScope.launch(Dispatchers.IO) {
            lessons = repository.getLessons()
        }
    }
}