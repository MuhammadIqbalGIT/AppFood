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

class SearchMealAdapter
    : ListAdapter<Meals, SearchMealAdapter.SearchMealViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchMealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DetailMealItemBinding.inflate(inflater, parent, false)
        return SearchMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMealViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item)


    }

    inner class SearchMealViewHolder(private val binding: DetailMealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindTo(
            item: Meals,
        ) {
            with(binding) {
                imageViewMealThumb.visibility =
                    if (item.strMealThumb.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewMealName.visibility =
                    if (item.strMeal.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewCategory.visibility =
                    if (item.strCategory.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewArea.visibility =
                    if (item.strArea.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewInstructions.visibility =
                    if (item.strInstructions.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewTags.visibility =
                    if (item.strTags.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewYoutube.visibility =
                    if (item.strYoutube.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewSource.visibility =
                    if (item.strSource.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewImageSource.visibility =
                    if (item.strImageSource.isNullOrEmpty()) View.GONE else View.VISIBLE
                textViewCreativeCommons.visibility =
                    if (item.strCreativeCommonsConfirmed.isNullOrEmpty()) View.GONE else View.VISIBLE

                imageViewMealThumb.load(item.strMealThumb)
                textViewMealName.text = item.strMeal
                textViewCategory.text = item.strCategory
                textViewArea.text = item.strArea
                textViewInstructions.text = item.strInstructions
                textViewTags.text = item.strTags
                textViewYoutube.text = item.strYoutube
                textViewSource.text = item.strSource
                textViewImageSource.text = item.strImageSource
                textViewCreativeCommons.text = item.strCreativeCommonsConfirmed
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
