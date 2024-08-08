package com.example.appfood.ui.filter_category.sheet

import AllCategoryResponse
import CategoryAll
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appfood.databinding.AllCategoryItemBinding
import com.example.appfood.databinding.CategoryItemBinding
import com.example.core.data.source.remote.response.Category

class AllCategoryAdapter
    : ListAdapter<CategoryAll, AllCategoryAdapter.AllCategoryViewHolder>(DiffCallback()) {


    lateinit var onClickListener: ((CategoryAll) -> Unit)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            AllCategoryItemBinding.inflate(inflater, parent, false)
        return AllCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllCategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item)


    }

    inner class AllCategoryViewHolder(private val binding: AllCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindTo(
            item: CategoryAll,
        ) {
            with(binding) {
                tvNameCategory.text = item.strCategory
                clCategory.setOnClickListener {
                    onClickListener.invoke(item)
                }
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<CategoryAll>() {
    override fun areItemsTheSame(oldItem: CategoryAll, newItem: CategoryAll): Boolean {
        return oldItem.strCategory == newItem.strCategory
    }

    override fun areContentsTheSame(oldItem: CategoryAll, newItem: CategoryAll): Boolean {
        return oldItem == newItem
    }
}






