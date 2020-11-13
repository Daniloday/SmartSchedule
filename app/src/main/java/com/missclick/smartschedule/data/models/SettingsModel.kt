package com.missclick.smartschedule.data.models

data class SettingsModel(
    var id : Int? = null,
    var days : Int,
    var couples : Int,
    var weeks : Int,
    var language : String = "en"
)