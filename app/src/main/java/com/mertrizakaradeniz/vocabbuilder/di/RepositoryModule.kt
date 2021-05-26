package com.mertrizakaradeniz.vocabbuilder.di

import com.mertrizakaradeniz.vocabbuilder.data.local.WordDao
import com.mertrizakaradeniz.vocabbuilder.data.repository.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWordRepository(
        wordDao: WordDao
    ): WordRepository = WordRepository(wordDao)

}