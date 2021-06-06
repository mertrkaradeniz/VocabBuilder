package com.mertrizakaradeniz.vocabbuilder.utils

import com.mertrizakaradeniz.vocabbuilder.data.model.Word

object Constant {

    const val DATABASE_NAME: String = "Word.db"
    const val NOTIFICATION_ID: Int = 0
    const val REQUEST_CODE: Int = 0
    const val MIN_QUIZ_QUESTION_COUNT: Int = 10
    const val CATEGORIES: String = "categories"
    const val QUESTION_COUNT: String = "question_count"
    const val NAME: String = "name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    const val PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE = 1

    var memorizeList: List<Word> = emptyList()

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

    private val read = Word(
        "read",
        "Verbs",
        "look at and comprehend the meaning of (written or printed matter) by interpreting the characters or symbols of which it is composed",
        "it's the best novel I've ever read",
        "",
        ""
    )

    private val drink = Word(
        "drink",
        "Verbs",
        "Take liquid into the mouth and swallow",
        "we sat by the fire, drinking our tea",
        "swallow",
        ""
    )

    private val eat = Word(
        "eat",
        "Verbs",
        "put food into the mouth and chew and swallow it",
        "he was eating a hot dog",
        "consume",
        "starve"
    )

    private val swim = Word(
        "swim",
        "Verbs",
        "propel the body through water by using the limbs, or (in the case of a fish or other aquatic animal) by using fins, tail, or other bodily movement",
        "they swam ashore",
        "",
        ""
    )

    private val hug = Word(
        "hug",
        "Verbs",
        "squeeze someone tightly in one's arms, typically to express affection",
        "he hugged her close to him",
        "embrace",
        "free"
    )
    private val laugh = Word(
        "laugh",
        "Verbs",
        "make the spontaneous sounds and movements of the face and body that are the instinctive expressions of lively amusement and sometimes also of derision",
        "he rarely smiled or laughed",
        "chuckle",
        "cry"
    )

    private val hammer = Word(
        "hammer",
        "Verbs",
        "a tool with a heavy metal head mounted at right angles at the end of a handle, used for jobs such as breaking things and driving in nails.",
        "He hammered the tack in",
        "beat",
        "praise"
    )
    private val cry = Word(
        "cry",
        "Verbs",
        "shed tears, typically as an expression of distress, pain, or sorrow",
        "don't cry",
        "nap",
        ""
    )

    private val sleep = Word(
        "sleep",
        "Verbs",
        "a condition of body and mind that typically recurs for several hours every night, in which the nervous system is relatively inactive, the eyes closed, the postural muscles relaxed, and consciousness practically suspended",
        "I was on the verge of sleep",
        "rest",
        "wake up"
    )

    private val knit = Word(
        "knit",
        "Verbs",
        "make (a garment, blanket, etc.) by interlocking loops of wool or other yarn with knitting needles or on a machine",
        "she was knitting a sweater",
        "unite",
        ""
    )

    private val bravely = Word(
        "bravely",
        "Adverbs",
        "in a way that shows a willingness to face and endure danger or pain; courageously",
        "he has been hailed a hero after he bravely tackled a thief at a hotel",
        "boldly",
        "fearfully"
    )

    private val cheerfully = Word(
        "cheerfully",
        "Adverbs",
        "in a cheerful way",
        "he was whistling cheerfully",
        "happily",
        "sadly"
    )

    private val elegantly = Word(
        "elegantly",
        "Adverbs",
        "in a graceful and stylish manner",
        "a handsome, elegantly dressed businessman",
        "gorgeously",
        "dashingly"
    )

    private val faithfully = Word(
        "faithfully",
        "Adverbs",
        "in a loyal manner",
        "he swore that he would serve the king faithfully",
        "accurate",
        "disloyal"
    )

    private val fortunately = Word(
        "fortunately",
        "Adverbs",
        "it is fortunate that",
        "fortunately, no shots were fired and no one was hurt",
        "blessedly",
        "unfortunately"
    )

    private val honestly = Word(
        "honestly",
        "Adverbs",
        "in a truthful, fair, or honourable way",
        "he'd come by the money honestly",
        "actually",
        ""
    )

    private val kindly = Word(
        "kindly",
        "Adverbs",
        "in a kind manner",
        "‘Never mind,’ she said kindly",
        "advantageous",
        "atrocious"
    )

    private val perfectly = Word(
        "perfectly",
        "Adverbs",
        "in a manner or way that could not be better",
        "the ring fitted perfectly",
        "faultlessly",
        "amiss"
    )

    private val politely = Word(
        "politely",
        "Adverbs",
        "in a respectful and considerate manner",
        "he waited politely for me to ask questions",
        "civilly",
        "arrogantly"
    )

    private val safely = Word(
        "safely",
        "Adverbs",
        "in a way that gives protection from danger or risk",
        "there is a memento she keeps safely stored away",
        "secure",
        "dangerous"
    )

    private val TheBestOfBothWorlds = Word(
        "The best of both worlds",
        "Phrases and Idioms",
        "you can enjoy two different opportunities at the same time",
        "By working part-time and looking after her kids two days a week she managed to get the best of both worlds",
        "",
        ""
    )

    private val SpeakOfTheDevil = Word(
        "Speak of the devil",
        "Phrases and Idioms",
        "the person you’re just talking about actually appears at that moment",
        "Hi Tom, speak of the devil, I was just telling Sara about your new car",
        "",
        ""
    )

    private val SeeEyeToEye = Word(
        "See eye to eye",
        "Phrases and Idioms",
        "agreeing with someone",
        "They finally saw eye to eye on the business deal",
        "",
        ""
    )

    private val OnceInABlueMoon = Word(
        "Once in a blue moon",
        "Phrases and Idioms",
        "an event that happens infrequently",
        "I only go to the cinema once in a blue moon",
        "",
        ""
    )

    private val WhenPigsFly = Word(
        "When pigs fly",
        "Phrases and Idioms",
        "something that will never happen",
        "When pigs fly she’ll tidy up her room",
        "",
        ""
    )

    private val ToCostAnArmAndALeg = Word(
        "To cost an arm and a leg",
        "Phrases and Idioms",
        "something is very expensive",
        "Fuel these days costs and arm and a leg",
        "",
        ""
    )

    private val APieceOfCake = Word(
        "A piece of cake",
        "Phrases and Idioms",
        "something is very easy",
        "The English test was a piece of cake",
        "",
        ""
    )

    private val LetTheCatOutOfTheBag = Word(
        "Let the cat out of the bag",
        "Phrases and Idioms",
        "to accidentally reveal a secret",
        "I let the cat out of the bag about their wedding plans",
        "",
        ""
    )

    private val ToFeelUnderTheWeather = Word(
        "To feel under the weather",
        "Phrases and Idioms",
        "to not feel well",
        "I’m really feeling under the weather today; I have a terrible cold",
        "",
        ""
    )

    private val ToKillTwoBirdsWithOneStone = Word(
        "To kill two birds with one stone",
        "Phrases and Idioms",
        "to solve two problems at once",
        "By taking my dad on holiday, I killed two birds with one stone. I got to go away but also spend time with him",
        "",
        ""
    )

    val wordList =
        listOf(
            angry,
            bad,
            calm,
            cheerful,
            clear,
            creepy,
            crazy,
            dangerous,
            dead,
            cute,
            read,
            drink,
            eat,
            swim,
            hug,
            laugh,
            hammer,
            cry,
            sleep,
            knit,
            bravely,
            cheerfully,
            elegantly,
            faithfully,
            fortunately,
            honestly,
            kindly,
            perfectly,
            politely,
            safely,
            TheBestOfBothWorlds,
            SpeakOfTheDevil,
            SeeEyeToEye,
            OnceInABlueMoon,
            WhenPigsFly,
            ToCostAnArmAndALeg,
            APieceOfCake,
            LetTheCatOutOfTheBag,
            ToFeelUnderTheWeather,
            ToKillTwoBirdsWithOneStone
        )


}