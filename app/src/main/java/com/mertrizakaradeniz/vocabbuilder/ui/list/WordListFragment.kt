package com.mertrizakaradeniz.vocabbuilder.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.adapters.WordListAdapter
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentWordListBinding
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.wordList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordListFragment : Fragment(R.layout.fragment_word_list) {

    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordAdapter: WordListAdapter
    private val viewModel: WordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
        handleClickEvent()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.addAllWords(wordList)
        viewModel.getAllWords.observe(requireActivity(), { words ->
            wordAdapter.words = words
        })
    }

    private fun handleClickEvent() {
        wordAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("word", it)
            }
            findNavController().navigate(
                R.id.action_wordListFragment_to_wordDetailFragment,
                bundle
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> findNavController().navigate(WordListFragmentDirections.actionWordListFragmentToAddWordFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        wordAdapter = WordListAdapter()
        binding.rvWordList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = wordAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}