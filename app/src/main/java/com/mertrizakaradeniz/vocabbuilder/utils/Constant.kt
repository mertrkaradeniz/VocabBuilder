package com.mertrizakaradeniz.vocabbuilder.utils

import com.mertrizakaradeniz.vocabbuilder.data.model.Word

object Constant {

    const val DATABASE_NAME: String = "Word.db"

    private val angry = Word(
        "angry",
        "Adjectives",
        "having strong feelings about something that you dislike very much or about an unfair situation",
        "Her behaviour really made me angry.",
        "mad",
        "calm"
    )

    private val bad = Word(
        "bad",
        "Adjectives",
        "of poor quality or a low standard",
        "a bad diet",
        "substandard",
        "good"
    )

    private val calm = Word(
        "calm",
        "Adjectives",
        "not showing or feeling nervousness, anger, or other strong emotions",
        "she had to keep calm at all costs",
        "windless",
        "windy"
    )

    private val cheerful = Word(
        "cheerful",
        "Adjectives",
        "noticeably happy and optimistic",
        "how can she be so cheerful at six o'clock in the morning?",
        "happy",
        "sad"
    )

    private val clear = Word(
        "clear",
        "Adjectives",
        "easy to perceive, understand, or interpret",
        "clear and precise directions",
        "understandable",
        "unclear"
    )

    private val creepy = Word(
        "creepy",
        "Adjectives",
        "causing an unpleasant feeling of fear or unease",
        "the creepy feelings one often gets in a strange house",
        "frightening",
        "relaxing"
    )

    private val crazy = Word(
        "crazy",
        "Adjectives",
        "mad, especially as manifested in wild or aggressive behaviour",
        "Stella went crazy and assaulted a visitor",
        "insane",
        "sane"
    )

    private val dangerous = Word(
        "dangerous",
        "Adjectives",
        "able or likely to cause harm or injury",
        "a dangerous animal",
        "menacing",
        "harmless"
    )

    private val dead = Word(
        "dead",
        "Adjectives",
        "no longer alive",
        "a dead body",
        "a dead body",
        "alive"
    )

    private val cute = Word(
        "cute",
        "Adjectives",
        "attractive in a pretty or endearing way",
        "she had a cute little nose",
        "sweet",
        "unattractive"
    )

    val wordList =
        listOf<Word>(angry, bad, calm, cheerful, clear, creepy, crazy, dangerous, dead, cute)

}