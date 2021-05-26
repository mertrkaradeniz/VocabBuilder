package com.mertrizakaradeniz.vocabbuilder.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mertrizakaradeniz.vocabbuilder.data.model.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWord(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllWords(list: List<Word>)

    @Query("SELECT * FROM words ORDER BY name DESC")
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM words WHERE name LIKE :searchQuery")
    fun searchWords(searchQuery: String): LiveData<List<Word>>

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("DELETE FROM words")
    suspend fun deleteAll()

}