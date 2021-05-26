package com.mertrizakaradeniz.vocabbuilder.di

import android.content.Context
import androidx.room.Room
import com.mertrizakaradeniz.vocabbuilder.data.local.WordDatabase
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideWordDatabase(@ApplicationContext context: Context): WordDatabase {
        return Room.databaseBuilder(
            context,
            WordDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideWordDao(wordDatabase: WordDatabase) = wordDatabase.getWordDao()

}