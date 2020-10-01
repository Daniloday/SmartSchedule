package com.missclick.smartschedule.di.module

import android.content.Context
import androidx.room.Room
import com.missclick.smartschedule.data.datasource.local.LocalDataSource
import com.missclick.smartschedule.data.datasource.local.ScheduleDatabase
import com.missclick.smartschedule.data.datasource.remote.RemoteDataSource
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.data.repository.LessonRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideILessonRepository(local : LocalDataSource, remote : RemoteDataSource) : ILessonRepository{
        return LessonRepository(local, remote)
    }

    @Provides
    fun provideLocalDataSource(appContext: Context) : LocalDataSource{
        val database = Room.databaseBuilder(appContext, ScheduleDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
        return LocalDataSource(database = database)
    }

    @Provides
    fun provideRemoteDataSource() : RemoteDataSource{
        return RemoteDataSource()
    }
}