package com.mertrizakaradeniz.vocabbuilder.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.mertrizakaradeniz.vocabbuilder.utils.createChannel
import com.mertrizakaradeniz.vocabbuilder.utils.sendNotification

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context?.let {
            ContextCompat.getSystemService(
                it,
                NotificationManager::class.java,
            )
        } as NotificationManager

        notificationManager.createChannel(context, "word_channel", "word")
        notificationManager.sendNotification(
            context,
            "Reminder",
            ""
        )
    }
}