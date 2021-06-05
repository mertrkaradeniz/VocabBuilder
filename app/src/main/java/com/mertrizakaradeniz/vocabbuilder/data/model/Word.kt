package com.mertrizakaradeniz.vocabbuilder.data.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "words")
data class Word(
    @PrimaryKey
    val name: String,
    val categories: String,
    val definition: String,
    val exampleSentence: String,
    val synonyms: String?,
    val antonyms: String?,
    var is_memorize: Boolean = false
) : Parcelable

