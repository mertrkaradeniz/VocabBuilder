package com.mertrizakaradeniz.vocabbuilder.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.data.model.Question
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentQuizBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordViewModel
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.CATEGORIES
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WordViewModel by viewModels()
    private lateinit var categories: String
    private lateinit var name: String
    private lateinit var wordList: List<Word>
    private var chosenWordList: ArrayList<Word> = ArrayList()
    private var questionList: ArrayList<Question> = ArrayList()
    private val randomIndexList = ArrayList<Int>()
    private var randomQuestionIndex = 0
    private lateinit var word: Word
    private val questionCount = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
        setupObservers()
        choseWords()
        createQuestions()
    }

    private fun getDataFromBundle() {
        categories = arguments?.getString(CATEGORIES, "").toString()
        name = arguments?.getString(NAME, "").toString()
    }

    private fun setupObservers() {
        viewModel.getWordsByCategories(categories).observe(viewLifecycleOwner) { list ->
            list?.let {
                wordList = it
            }
        }
    }

    private fun choseWords() {
        while (chosenWordList.size != questionCount) {
            do {
                word = wordList.random()
            } while (chosenWordList.contains(word))
            chosenWordList.add(word)
        }
    }

    private fun createQuestions() {
        chosenWordList.shuffle()
        for (i in 0 until questionCount) {
            val correctAnswerWord = chosenWordList[i]
            randomQuestionIndex = i
            randomIndexList.add(randomQuestionIndex)
            randomIndex()
            val optionsFirstWord = chosenWordList[randomQuestionIndex]
            randomIndex()
            randomIndexList.add(randomQuestionIndex)
            val optionsSecondWord = chosenWordList[randomQuestionIndex]
            randomIndex()
            val optionsThirdWord = chosenWordList[randomQuestionIndex]
            when (val correctAnswerIndex = (1..4).random()) {
                1 -> {
                    val question = Question(
                        correctAnswerWord.definition,
                        correctAnswerWord.name,
                        optionsFirstWord.name,
                        optionsSecondWord.name,
                        optionsThirdWord.name,
                        correctAnswerIndex
                    )
                    questionList.add(question)
                }
                2 -> {
                    val question = Question(
                        correctAnswerWord.definition,
                        optionsFirstWord.name,
                        correctAnswerWord.name,
                        optionsSecondWord.name,
                        optionsThirdWord.name,
                        correctAnswerIndex
                    )
                    questionList.add(question)
                }
                3 -> {
                    val question = Question(
                        correctAnswerWord.definition,
                        optionsSecondWord.name,
                        optionsFirstWord.name,
                        correctAnswerWord.name,
                        optionsThirdWord.name,
                        correctAnswerIndex
                    )
                    questionList.add(question)
                }
                4 -> {
                    val question = Question(
                        correctAnswerWord.definition,
                        optionsThirdWord.name,
                        optionsFirstWord.name,
                        optionsSecondWord.name,
                        correctAnswerWord.name,
                        correctAnswerIndex
                    )
                    questionList.add(question)
                }
            }
            randomIndexList.clear()
        }
    }

    private fun randomIndex() {
        do {
            randomQuestionIndex = (0..9).random()
        } while (randomIndexList.contains(randomQuestionIndex))
        randomIndexList.add(randomQuestionIndex)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}