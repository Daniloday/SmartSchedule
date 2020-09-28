package com.missclick.smartschedule.di.module

import com.missclick.smartschedule.data.datasource.local.LocalDataSource
import com.missclick.smartschedule.data.datasource.remote.RemoteDataSource
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.data.repository.LessonRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideLessonRepository(local : LocalDataSource, remote : RemoteDataSource) : ILessonRepository{
        return LessonRepository(local, remote)
    }

    @Provides
    fun provideLocalDataSource() : LocalDataSource{
        return LocalDataSource()
    }

    @Provides
    fun provideRemoteDataSource() : RemoteDataSource{
        return RemoteDataSource()
    }
}