package com.mertrizakaradeniz.vocabbuilder.ui.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentWordQuizBinding


class WordQuizFragment : Fragment(R.layout.fragment_word_quiz) {

    private var _binding: FragmentWordQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClickEvent()
    }

    private fun handleClickEvent() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(
                R.id.action_wordQuizFragment_to_quizFragment//,
                //bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}