package com.mertrizakaradeniz.vocabbuilder.ui.list

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
    private lateinit var sharedPref: SharedPreferences
    private var isFirstTime: Boolean = true

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
        setupSharedPref()
        setupRecyclerView()
        handleClickEvent()
        setupObservers()
        setupItemTouchEvent()
    }

    private fun setupSharedPref() {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        isFirstTime = sharedPref.getBoolean(getString(R.string.first_time), true)
    }

    private fun setupObservers() {
        if (isFirstTime) {
            sharedPref.edit().putBoolean(getString(R.string.first_time), false).apply()
            viewModel.addAllWords(wordList)
        }
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

    private fun setupItemTouchEvent() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val word = wordAdapter.words[position]
                viewModel.deleteWord(word)
                Snackbar.make(binding.root, "Successfully deleted word", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo") {
                            viewModel.upsertWord(word)
                        }
                        setActionTextColor(ContextCompat.getColor(context, R.color.yellow))
                        show()
                    }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvWordList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}