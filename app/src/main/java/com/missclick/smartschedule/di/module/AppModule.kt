package com.missclick.smartschedule.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.ui.mainScreen.MainScreenFragment
import com.missclick.smartschedule.ui.mainScreen.MainScreenViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class AppModule(context: Context) {

    private var appContext : Context = context

    @Provides
    @Singleton
    fun provideContext() : Context{
        return appContext
    }

}