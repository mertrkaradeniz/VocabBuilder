package com.mertrizakaradeniz.vocabbuilder.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentWordQuizBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordViewModel
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.CATEGORIES
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.MIN_QUIZ_QUESTION_COUNT
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.NAME
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.QUESTION_COUNT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordQuizFragment : Fragment(R.layout.fragment_word_quiz) {

    private var _binding: FragmentWordQuizBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WordViewModel by viewModels()
    private lateinit var wordList: List<Word>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCategoriesSpinner()
        handleClickEvent()
        //setup()
    }

    private fun handleClickEvent() {
        binding.btnStart.setOnClickListener {
            if (binding.etName.text.toString().isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString(NAME, binding.etName.text.toString())
                    putString(CATEGORIES, binding.spCategories.selectedItem.toString())
                    putInt(QUESTION_COUNT, binding.spCount.selectedItem.toString().toInt())
                }
                findNavController().navigate(
                    R.id.action_wordQuizFragment_to_quizFragment,
                    bundle
                )
            } else {
                Toast.makeText(requireContext(), "Please enter your name!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupCategoriesSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.categories,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spCategories.adapter = adapter
        }
        binding.spCategories.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) = handleSpinnerEvent(position)

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setupQuestionCountSpinner() {
        val countArray = ArrayList<String>()
        for (i in MIN_QUIZ_QUESTION_COUNT..wordList.size) {
            countArray.add(i.toString())
        }
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            countArray
        )
        binding.spCount.adapter = arrayAdapter

    }

    private fun handleSpinnerEvent(position: Int) {
        viewModel.getWordsByCategories(binding.spCategories.selectedItem.toString())
            .observe(viewLifecycleOwner) { list ->
                wordList = list
                if (position > 0) {
                    setupQuestionCountSpinner()
                    if (wordList.size >= MIN_QUIZ_QUESTION_COUNT) {
                        showQuestionCount()
                    } else {
                        hideQuestionCount()
                        Toast.makeText(
                            requireContext(),
                            "There must be at least 10 words in this category",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else if (position == 0) {
                    hideQuestionCount()
                }
            }
    }

    private fun showQuestionCount() {
        binding.apply {
            binding.btnStart.isEnabled = true
            spCount.visibility = View.VISIBLE
            tvQuestionCount.visibility = View.VISIBLE
        }
    }

    private fun hideQuestionCount() {
        binding.apply {
            binding.btnStart.isEnabled = false
            spCount.visibility = View.GONE
            tvQuestionCount.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}