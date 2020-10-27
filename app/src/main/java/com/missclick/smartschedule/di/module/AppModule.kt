package com.missclick.smartschedule.di.module

import android.content.Context
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