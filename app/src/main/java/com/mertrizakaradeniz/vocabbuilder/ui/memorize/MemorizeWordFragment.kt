package com.mertrizakaradeniz.vocabbuilder.ui.memorize

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.adapters.WordListAdapter
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentMemorizeWordBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemorizeWordFragment : Fragment(R.layout.fragment_memorize_word) {

    private var _binding: FragmentMemorizeWordBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordAdapter: WordListAdapter
    private val viewModel: WordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemorizeWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        wordAdapter = WordListAdapter()
        binding.rvMemorize.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = wordAdapter
        }
        viewModel.getAllMemorizedWords.observe(requireActivity(), { memorizedWords ->
            wordAdapter.words = memorizedWords
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}