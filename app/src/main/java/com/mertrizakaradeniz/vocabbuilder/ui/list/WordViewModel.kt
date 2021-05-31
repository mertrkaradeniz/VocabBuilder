package com.mertrizakaradeniz.vocabbuilder.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.data.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val repository: WordRepository
) : ViewModel() {

    val getAllWords: LiveData<List<Word>> = repository.getAllWords()
    val getAllMemorizedWords: LiveData<List<Word>> = repository.getAllMemorizedWords()

    fun upsertWord(word: Word) = viewModelScope.launch {
        repository.upsertWord(word)
    }

    fun addAllWords(list: List<Word>) = viewModelScope.launch {
        repository.addAllWords(list)
    }

    fun searchWords(searchQuery: String): LiveData<List<Word>> {
        return repository.searchWords(searchQuery)
    }

    fun deleteWord(word: Word) = viewModelScope.launch {
        repository.deleteWord(word)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun checkWordIsMemorized(name: String): LiveData<List<Word>> {
        return repository.checkWordIsMemorized(name)
    }

}