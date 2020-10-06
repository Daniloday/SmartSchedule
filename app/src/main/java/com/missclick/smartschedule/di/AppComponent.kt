package com.missclick.smartschedule.di

import com.missclick.smartschedule.di.module.AppModule
import com.missclick.smartschedule.di.module.RepositoryModule
import com.missclick.smartschedule.ui.lessons.LessonsViewModel
import com.missclick.smartschedule.ui.lessons.addLesson.AddLessonViewModel
import com.missclick.smartschedule.ui.lessons.edit.EditLessonViewModel
import com.missclick.smartschedule.ui.mainScreen.MainScreenViewModel
import com.missclick.smartschedule.ui.mainScreen.schedule.ScheduleViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RepositoryModule::class])
@Singleton
interface AppComponent{
    fun inject(mainScreenViewModel: MainScreenViewModel)
    fun inject(lessonViewModel: LessonsViewModel)
    fun inject(addLessonViewModel: AddLessonViewModel)
    fun inject(editLessonViewModel: EditLessonViewModel)
    fun inject(scheduleViewModel: ScheduleViewModel)
}