package com.rdcmind.coopeccollect.di

import com.rdcmind.coopeccollect.data.CoopecRepsitoryImpl
import com.rdcmind.coopeccollect.domain.repository.CoopecRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoopecRepository(
        coopecRepositoryImpl: CoopecRepsitoryImpl
    ) : CoopecRepository

}