package com.mertrizakaradeniz.vocabbuilder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertrizakaradeniz.vocabbuilder.data.model.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao
}