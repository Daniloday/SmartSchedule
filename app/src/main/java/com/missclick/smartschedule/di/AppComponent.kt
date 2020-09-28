package com.missclick.smartschedule.di

import com.missclick.smartschedule.di.module.AppModule
import com.missclick.smartschedule.di.module.RepositoryModule
import com.missclick.smartschedule.ui.mainScreen.MainScreenFragment
import dagger.Component

@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent{
    fun inject(mainScreenFragment: MainScreenFragment)
}