package com.example.appfood.ui.all_meal_category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appfood.databinding.CategoryItemBinding
import com.example.core.data.source.remote.response.Category

class CategoryAdapter
    : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            CategoryItemBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item)
    }

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindTo(
            item: Category,
        ) {
            with(binding) {
                tvMealName.text = item.strCategory
                imgMeal.load(item.strCategoryThumb)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}

