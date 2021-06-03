package com.mertrizakaradeniz.vocabbuilder.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.ui.main.MainActivity
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.NOTIFICATION_ID
import com.mertrizakaradeniz.vocabbuilder.utils.Data.memorizeList

fun NotificationManager.sendNotification(
    applicationContext: Context,
    messageTitle: String,
    messageBody: String
) {
    val word = memorizeList.random()
    val bundle = Bundle().apply {
        putParcelable("word", word)
    }
    val contentPendingIntent = NavDeepLinkBuilder(applicationContext)
        .setComponentName(MainActivity::class.java)
        .setArguments(bundle)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.wordDetailFragment)
        .createPendingIntent()

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.word_notification_channel_id)
    )
        .setSmallIcon(R.drawable.ic_memorize)
        .setContentTitle(messageTitle)
        .setContentText("Memorize this word: ${word.name}")
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}