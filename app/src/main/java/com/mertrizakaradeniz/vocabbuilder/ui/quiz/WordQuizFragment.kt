package com.mertrizakaradeniz.vocabbuilder.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentWordQuizBinding
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.CATEGORIES
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.NAME


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
            //if (binding.etName.text.toString().isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString(NAME, binding.etName.text.toString())
                    putString(CATEGORIES, binding.spCategories.selectedItem.toString())
                }
                findNavController().navigate(
                    R.id.action_wordQuizFragment_to_quizFragment,
                    bundle
                )
            /*} else {
                Toast.makeText(requireContext(), "Please enter your name!", Toast.LENGTH_SHORT).show()
            }*/

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}