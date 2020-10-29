package com.missclick.smartschedule.ui.importScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.repository.ILessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImportViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    private val _text = MutableLiveData<String>().apply {
        value = "This is import Fragment"
    }
    val text: LiveData<String> = _text

    init {
        App.appComponent.inject(this)
    }

    fun import(id : String){
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                repository.importSchedule(id = id)
            }
        }
    }
}