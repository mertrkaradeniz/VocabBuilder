package com.mertrizakaradeniz.vocabbuilder.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentWordDetailBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordDetailFragment : Fragment(R.layout.fragment_word_detail) {

    private var _binding: FragmentWordDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WordViewModel by viewModels()
    private lateinit var word: Word
    private var isMemorize: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        word = arguments?.get("word") as Word
        setupObservers()
        populateUI()
    }

    private fun setupObservers() {
        viewModel.checkWordIsMemorized(word.name).observe(viewLifecycleOwner) { list ->
            list?.let {
                isMemorize = list.isNotEmpty()
                setupButton()
            }
        }
    }

    private fun setupButton() {
        if (isMemorize) {
            "Done/Memorized".also { binding.btnMemorize.text = it }
        } else {
            "Cancel/Remind Later".also { binding.btnMemorize.text = it }
        }
        handleClickEvent()
    }

    private fun handleClickEvent() {
        binding.btnMemorize.setOnClickListener {
            if (isMemorize) {
                word.is_memorize = false
                isMemorize = false
                viewModel.upsertWord(word)
                Snackbar.make(
                    binding.root,
                    "Word removed successfully in memorize",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            } else {
                word.is_memorize = true
                isMemorize = true
                viewModel.upsertWord(word)
                Snackbar.make(
                    binding.root,
                    "Word added memorize successfully",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun populateUI() {
        binding.apply {
            "Word: ${word.name}".also { tvName.text = it }
            "Definition: ${word.definition}".also { tvDefinition.text = it }
            "Categories: ${word.categories}".also { tvCategories.text = it }
            "Sentence: ${word.exampleSentence}".also { tvSentence.text = it }
            "Antonyms: ${word.antonyms}".also { tvAntonyms.text = it }
            "Synonyms: ${word.synonyms}".also { tvSynonyms.text = it }

            imgReminder.load(word.imgUrl) {
                crossfade(true)
                crossfade(1000)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}