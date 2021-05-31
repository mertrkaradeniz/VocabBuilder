package com.mertrizakaradeniz.vocabbuilder.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.databinding.WordItemBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordListFragmentDirections

class WordListAdapter : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: WordItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
    var words: List<Word>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            WordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentWord = words[position]

        holder.binding.apply {
            tvName.text = "Word: ${currentWord.name}"
            tvCategories.text = "Categories: ${currentWord.categories}"
        }

        holder.itemView.setOnClickListener { mView ->
            val bundle = Bundle().apply {
                putParcelable("word", currentWord)
            }
            mView.findNavController().navigate(
                R.id.action_wordListFragment_to_wordDetailFragment,
                bundle
            )
        }
    }

    override fun getItemCount() = words.size


}