package com.mertrizakaradeniz.vocabbuilder.ui.memorize

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.adapters.WordListAdapter
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentMemorizeWordBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordViewModel
import com.mertrizakaradeniz.vocabbuilder.utils.Data.memorizeList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemorizeWordFragment : Fragment(R.layout.fragment_memorize_word) {

    private var _binding: FragmentMemorizeWordBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordAdapter: WordListAdapter
    private val viewModel: WordViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences
    private var remind: Boolean = false

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
        setupButton()
        setupObservers()
    }

    private fun setupButton() {
        setupSharedPref()
        if (remind) {
            binding.btnReminder.text = getString(R.string.cancel_reminder)
        } else {
            binding.btnReminder.text = getString(R.string.remind_me)
        }
        handleClickEvent()
    }

    private fun setupSharedPref() {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        remind = sharedPref.getBoolean(getString(R.string.remind), false)
    }

    private fun setupObservers() {
        viewModel.getAllMemorizedWords.observe(requireActivity(), { memorizedWords ->
            memorizeList = memorizedWords
            wordAdapter.words = memorizedWords
        })
    }

    private fun handleClickEvent() {
        binding.btnReminder.setOnClickListener {
            if (remind) {
                binding.btnReminder.text = getString(R.string.remind_me)
                remind = false
                viewModel.cancelNotification()
                Snackbar.make(
                    binding.root,
                    "Reminder canceled successfully",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                binding.btnReminder.text = getString(R.string.cancel_reminder)
                remind = true
                viewModel.setupNotification()
                Snackbar.make(
                    binding.root,
                    "Reminder set successfully(twice a day)",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        wordAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("word", it)
            }
            findNavController().navigate(
                R.id.action_memorizeWordFragment_to_wordDetailFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        wordAdapter = WordListAdapter()
        binding.rvMemorize.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = wordAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sharedPref.edit().putBoolean(getString(R.string.remind), remind).apply()
        _binding = null
    }

}