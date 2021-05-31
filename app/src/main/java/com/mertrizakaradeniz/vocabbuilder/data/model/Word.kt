package com.mertrizakaradeniz.vocabbuilder.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "words")
data class Word(
    @PrimaryKey()
    val name: String,
    val categories: String,
    val definition: String,
    val synonyms: String?,
    val antonyms: String?,
    val exampleSentence: String?,
    val imgUrl: String?,
    val is_memorize: Boolean = false
) : Parcelable

