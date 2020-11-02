package com.missclick.smartschedule.data.datasource.remote.remoteModels

import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity

data class ScheduleFB (var lessons : List<LessonEntity>? = null, val days : List<DayEntity>? = null)