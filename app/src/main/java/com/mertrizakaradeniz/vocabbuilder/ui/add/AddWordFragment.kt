package com.mertrizakaradeniz.vocabbuilder.ui.add

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
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentAddWordBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWordFragment : Fragment(R.layout.fragment_add_word) {

    private var _binding: FragmentAddWordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WordViewModel by viewModels()
    private lateinit var word: Word

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            binding.apply {
                val name = etWord.text.toString()
                val definition = etDefinition.text.toString()
                val sentence = etSentence.text.toString()
                if (name.isNotEmpty() && definition.isNotEmpty() && sentence.isNotEmpty()) {
                    word = Word(
                        name,
                        spCategories.selectedItem.toString(),
                        definition,
                        sentence,
                        etSynonyms.text.toString(),
                        etAntonyms.text.toString(),
                    )
                    viewModel.upsertWord(word)
                    Toast.makeText(
                        requireContext(),
                        "Word is saved successfully.",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(AddWordFragmentDirections.actionAddWordFragmentToWordListFragment())
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Name, definition and sentence cannot be empty.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}