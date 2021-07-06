package com.example.roomhilt.module

import com.example.roomhilt.repo.BaseRepo
import com.example.roomhilt.repo.CustomRepo
import com.example.roomhilt.room.CustomDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    public fun provideCustomRepository(customDao : CustomDAO) : BaseRepo {
            return CustomRepo(customDao)
    }

}