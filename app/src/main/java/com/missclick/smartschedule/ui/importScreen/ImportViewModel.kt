package com.missclick.smartschedule.ui.importScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.viewstates.ExportViewStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImportViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    val error = MutableLiveData<String>()

    init {
        App.appComponent.inject(this)
    }

    fun import(id : String){
        GlobalScope.launch(Dispatchers.IO) {
            if (!repository.isOnline()) {
                withContext(Dispatchers.Main){
                    error.value = "Беды с башкой(интернетом)"
                }
                return@launch
            }
            repository.importSchedule(id = id)
            withContext(Dispatchers.Main){
                error.value = ""

            }
        }
    }
}