package com.missclick.smartschedule.di

import com.missclick.smartschedule.data.models.Schedule
import com.missclick.smartschedule.di.module.AppModule
import com.missclick.smartschedule.di.module.RepositoryModule
import com.missclick.smartschedule.ui.mainScreen.MainScreenFragment
import com.missclick.smartschedule.ui.mainScreen.MainScreenViewModel
import com.missclick.smartschedule.ui.schedule.ScheduleViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RepositoryModule::class])
@Singleton
interface AppComponent{
    fun inject(mainScreenViewModel: MainScreenViewModel)

}