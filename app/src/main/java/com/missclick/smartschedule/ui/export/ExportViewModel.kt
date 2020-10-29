package com.missclick.smartschedule.ui.export

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.repository.ILessonRepository
import javax.inject.Inject

class ExportViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    private val _text = MutableLiveData<String>().apply {
        value = "This is export Fragment"
    }
    val text: LiveData<String> = _text

    init {
        App.appComponent.inject(this)
    }

    fun export(){
        //todo command to repository
    }
}