package com.example.appfood.ui.search_meal

import Meals
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appfood.databinding.DetailMealItemBinding
import com.example.appfood.databinding.SearchMealItemBinding

class SearchMealAdapter
    : ListAdapter<Meals, SearchMealAdapter.SearchMealViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchMealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            SearchMealItemBinding.inflate(inflater, parent, false)
        return SearchMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMealViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item)


    }

    inner class SearchMealViewHolder(private val binding: SearchMealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindTo(
            item: Meals,
        ) {
            with(binding) {
                collapsingToolbar.title = item.strMeal
                imgMealDetail.load(item.strMealThumb)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Meals>() {
    override fun areItemsTheSame(oldItem: Meals, newItem: Meals): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meals, newItem: Meals): Boolean {
        return oldItem == newItem
    }
}
