package com.missclick.smartschedule.ui.export

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

class ExportViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    val idCode = MutableLiveData<String>()

    init {
        App.appComponent.inject(this)
    }

    fun export(){
        GlobalScope.launch(Dispatchers.IO) {
            val id = repository.exportSchedule()
            withContext(Dispatchers.Main){
                idCode.value = id
            }
        }
    }

}