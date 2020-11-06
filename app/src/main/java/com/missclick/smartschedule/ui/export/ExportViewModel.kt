package com.missclick.smartschedule.ui.export

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.extensions.default
import com.missclick.smartschedule.viewstates.ExportViewStates
import kotlinx.coroutines.*
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress
import javax.inject.Inject

class ExportViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    val idCode = MutableLiveData<String>()
    val stateData = MutableLiveData<ExportViewStates>().default(initialValue = ExportViewStates.LoadingState)

    init {
        App.appComponent.inject(this)
    }


    fun export(){
        GlobalScope.launch(Dispatchers.IO) {
            if (!repository.isOnline()) {
                withContext(Dispatchers.Main){
                    stateData.value = ExportViewStates.ErrorState("Беды с башкой(интернетом)")

                }
                return@launch
            }
            val id = repository.exportSchedule()
            withContext(Dispatchers.Main){
                if (id != null) stateData.value = ExportViewStates.LoadedState(id)
                else stateData.value = ExportViewStates.ErrorState("Error")
            }
        }
    }

}