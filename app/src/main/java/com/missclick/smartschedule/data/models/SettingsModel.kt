package com.missclick.smartschedule.data.models

data class SettingsModel(
    var id : Int? = null,
    var days : List<String>,
    var couples : Int,
    var weeks : Int
)