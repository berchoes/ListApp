package com.example.listapp.presentation.home

/**
 * Created by Berk Ç. on 9.04.2022.
 */

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.databinding.ItemPostListBinding
import com.example.listapp.domain.model.PostModel
import javax.inject.Inject


class PostListAdapter @Inject constructor() : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private var items = mutableListOf<PostModel>()
    var onItemClicked: ((PostModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemPostListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemPostListBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostModel) {

            binding.root.setOnClickListener {
                onItemClicked?.apply { this(item) }
            }

            binding.tvItem.text = item.title

            binding.icDelete.setOnClickListener {
                deleteItem(adapterPosition)
            }
        }
    }

    private fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<PostModel>) {
        items = list.toMutableList()
        notifyDataSetChanged()
    }
}