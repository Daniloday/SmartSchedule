package com.missclick.smartschedule.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.ui.mainScreen.MainScreenFragment
import com.missclick.smartschedule.ui.mainScreen.MainScreenViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(context: Context) {

    private var appContext : Context = context

    @Provides
    @Singleton
    fun provideContext() : Context{
        return appContext
    }

    @Provides
    @Singleton
    fun provideMainScreenViewModel(repository: ILessonRepository) : MainScreenViewModel{
        return MainScreenViewModel(repository = repository)
    }


}