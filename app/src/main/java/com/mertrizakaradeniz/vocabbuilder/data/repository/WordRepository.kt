package com.mertrizakaradeniz.vocabbuilder.data.repository

import com.mertrizakaradeniz.vocabbuilder.data.local.WordDao
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao
) {

    suspend fun upsertWord(word: Word) = wordDao.upsertWord(word)

    suspend fun addAllWords(list: List<Word>) = wordDao.addAllWords(list)

    fun getAllWords() = wordDao.getAllWords()

//    fun searchWords(searchQuery: String) = wordDao.searchWords(searchQuery)

    suspend fun deleteWord(word: Word) = wordDao.deleteWord(word)

//    suspend fun deleteAll() = wordDao.deleteAll()

    fun getAllMemorizedWords() = wordDao.getAllMemorizedWords()

    fun getWordsByCategories(categories: String) = wordDao.getWordsByCategories(categories)

    fun checkWordIsMemorized(name: String) = wordDao.checkWordIsMemorized(name)
}