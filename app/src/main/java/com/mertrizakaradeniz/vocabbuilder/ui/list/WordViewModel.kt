package com.mertrizakaradeniz.vocabbuilder.ui.list

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mertrizakaradeniz.vocabbuilder.AlarmReceiver
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.data.repository.WordRepository
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.REQUEST_CODE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val repository: WordRepository,
    app: Application
) : AndroidViewModel(app) {

    private val alarmManager: AlarmManager =
        app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val notificationIntent = Intent(app, AlarmReceiver::class.java)
    private val notifyPendingIntent: PendingIntent = PendingIntent.getBroadcast(
        app,
        REQUEST_CODE,
        notificationIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    fun setupNotification() {
//        alarmManager.setInexactRepeating(
//            AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            SystemClock.elapsedRealtime() + 1000 * 30,
//            1000 * 30,
//            notifyPendingIntent
//        )

        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_DAY,
            AlarmManager.INTERVAL_HALF_DAY,
            notifyPendingIntent
        )

//        val calendar: Calendar = Calendar.getInstance().apply {
//            timeInMillis = System.currentTimeMillis()
//            set(Calendar.HOUR_OF_DAY, 14)
//        }
//        alarmManager.setInexactRepeating(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            AlarmManager.INTERVAL_HALF_DAY,
//            notifyPendingIntent
//        )
    }

    fun cancelNotification() {
        alarmManager.cancel(notifyPendingIntent)
    }

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