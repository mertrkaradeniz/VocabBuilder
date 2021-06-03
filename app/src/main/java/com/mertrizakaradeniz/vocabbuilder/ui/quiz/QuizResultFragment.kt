package com.mertrizakaradeniz.vocabbuilder.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentQuizResultBinding
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.CORRECT_ANSWERS
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.NAME
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.TOTAL_QUESTIONS


class QuizResultFragment : Fragment() {

    private var _binding: FragmentQuizResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var name: String
    private lateinit var correctAnswers: String
    private lateinit var totalQuestions: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
        populateUI()
        handleClickEvent()
    }

    private fun handleClickEvent() {
        binding.btnFinish.setOnClickListener {
            findNavController().navigate(
                R.id.action_quizResultFragment_to_wordQuizFragment
            )
        }
    }

    private fun populateUI() {
        binding.apply {
            tvName.text = name
            tvScore.text = "Your Score is $correctAnswers out of $totalQuestions."
        }
    }

    private fun getDataFromBundle() {
        name = arguments?.getString(NAME, "").toString()
        correctAnswers = arguments?.getInt(CORRECT_ANSWERS, 0).toString()
        totalQuestions = arguments?.getInt(TOTAL_QUESTIONS, 0).toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}