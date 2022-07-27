package com.example.apibasic.overview

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apibasic.databinding.ListItemBinding
import com.example.apibasic.network.GithubData


class ItemAdapter(private val showDetail: (String) -> Unit) : ListAdapter<GithubData, ItemAdapter.GithubViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return GithubViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class GithubViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(githubData: GithubData) {
            binding.item = githubData

            binding.ghAvatar.setOnClickListener{
                showDetail(binding.ghUsername.text.toString())
            }

            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GithubData>() {
        override fun areItemsTheSame(oldItem: GithubData, newItem: GithubData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GithubData, newItem: GithubData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}