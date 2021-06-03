package com.mertrizakaradeniz.vocabbuilder.ui.quiz

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.data.model.Question
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentQuizBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordViewModel
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.CATEGORIES
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.CORRECT_ANSWERS
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.NAME
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.TOTAL_QUESTIONS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz), View.OnClickListener {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WordViewModel by viewModels()

    private lateinit var categories: String
    private lateinit var name: String
    private lateinit var wordList: List<Word>
    private lateinit var word: Word
    private var chosenWordList: ArrayList<Word> = ArrayList()
    private var questionsList: ArrayList<Question> = ArrayList()
    private val randomIndexList = ArrayList<Int>()
    private var randomQuestionIndex = 0
    private var currentPosition: Int = 1
    private var selectedOptionPosition: Int = 0
    private var correctAnswers: Int = 0
    private val questionCount = 10
    private var question = "Which of the following words means "

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
        handleClickEvent()
    }

    private fun setupObservers() {
        viewModel.getWordsByCategories(categories).observe(viewLifecycleOwner) { list ->
            list?.let {
                wordList = it
            }
            choseWords()
        }
    }

    private fun choseWords() {
        while (chosenWordList.size != questionCount) {
            do {
                word = wordList.random()
            } while (chosenWordList.contains(word))
            chosenWordList.add(word)
        }
        createQuestions()
    }

    private fun randomIndex() {
        do {
            randomQuestionIndex = (0..9).random()
        } while (randomIndexList.contains(randomQuestionIndex))
        randomIndexList.add(randomQuestionIndex)
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
                        question + correctAnswerWord.definition,
                        correctAnswerWord.name,
                        optionsFirstWord.name,
                        optionsSecondWord.name,
                        optionsThirdWord.name,
                        correctAnswerIndex
                    )
                    questionsList.add(question)
                }
                2 -> {
                    val question = Question(
                        question + correctAnswerWord.definition,
                        optionsFirstWord.name,
                        correctAnswerWord.name,
                        optionsSecondWord.name,
                        optionsThirdWord.name,
                        correctAnswerIndex
                    )
                    questionsList.add(question)
                }
                3 -> {
                    val question = Question(
                        question + correctAnswerWord.definition,
                        optionsSecondWord.name,
                        optionsFirstWord.name,
                        correctAnswerWord.name,
                        optionsThirdWord.name,
                        correctAnswerIndex
                    )
                    questionsList.add(question)
                }
                4 -> {
                    val question = Question(
                        question + correctAnswerWord.definition,
                        optionsThirdWord.name,
                        optionsFirstWord.name,
                        optionsSecondWord.name,
                        correctAnswerWord.name,
                        correctAnswerIndex
                    )
                    questionsList.add(question)
                }
            }
            randomIndexList.clear()
        }
        setQuestion()
    }

    private fun setQuestion() {
        val question = questionsList[currentPosition - 1]
        defaultOptionsView()

        binding.apply {
            if (currentPosition == questionsList.size) {
                binding.btnSubmit.text = getString(R.string.finish)
            } else {
                binding.btnSubmit.text = getString(R.string.submit)
            }
            pbQuestion.progress = currentPosition
            ("$currentPosition" + "/" + pbQuestion.max).also { tvProgress.text = it }
            tvQuestion.text = question.question
            //imgQuestion.setImageResource(question.image)
            tvOptionOne.text = question.optionOne
            tvOptionTwo.text = question.optionTwo
            tvOptionThree.text = question.optionThree
            tvOptionFour.text = question.optionFour
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        binding.apply {
            options.add(0, tvOptionOne)
            options.add(1, tvOptionTwo)
            options.add(2, tvOptionThree)
            options.add(3, tvOptionFour)
        }
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun handleClickEvent() {
        binding.apply {
            tvOptionOne.setOnClickListener(this@QuizFragment)
            tvOptionTwo.setOnClickListener(this@QuizFragment)
            tvOptionThree.setOnClickListener(this@QuizFragment)
            tvOptionFour.setOnClickListener(this@QuizFragment)
            btnSubmit.setOnClickListener(this@QuizFragment)
        }
    }

    private fun getDataFromBundle() {
        categories = arguments?.getString(CATEGORIES, "").toString()
        name = arguments?.getString(NAME, "").toString()
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        selectedOptionPosition = selectedOptionNum
        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.selected_option_border_bg
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(binding.tvOptionOne, 1)
            }
            R.id.tvOptionTwo -> {
                selectedOptionView(binding.tvOptionTwo, 2)
            }
            R.id.tvOptionThree -> {
                selectedOptionView(binding.tvOptionThree, 3)
            }
            R.id.tvOptionFour -> {
                selectedOptionView(binding.tvOptionFour, 4)
            }
            R.id.btnSubmit -> {
                if (selectedOptionPosition == 0) {
                    currentPosition++
                    when {
                        currentPosition <= questionsList.size -> {
                            setQuestion()
                        }
                        else -> {
                            val bundle = Bundle().apply {
                                putString(NAME, name)
                                putInt(CORRECT_ANSWERS, correctAnswers)
                                putInt(TOTAL_QUESTIONS, questionsList.size)
                            }
                            findNavController().navigate(
                                R.id.action_quizFragment_to_quizResultFragment,
                                bundle
                            )
                        }
                    }
                } else {
                    val question = questionsList[currentPosition - 1]
                    // This is to check if the answer is wrong
                    if (question.correctAnswer != selectedOptionPosition) {
                        answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        correctAnswers++
                    }
                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    if (currentPosition == questionsList.size) {
                        binding.btnSubmit.text = getString(R.string.finish)
                    } else {
                        binding.btnSubmit.text = getString(R.string.go_to_next_question)
                    }
                    selectedOptionPosition = 0
                }
            }
        }
    }
}