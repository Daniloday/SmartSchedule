package com.missclick.smartschedule.ui.share

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExportViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is export Fragment"
    }
    val text: LiveData<String> = _text
}