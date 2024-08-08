package com.example.appfood.ui.detail_meal

import Meals
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appfood.databinding.DetailMealItemBinding


class DetailMealAdapter
    : ListAdapter<Meals, DetailMealAdapter.DetailMealViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailMealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DetailMealItemBinding.inflate(inflater, parent, false)
        return DetailMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailMealViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item)
    }

    inner class DetailMealViewHolder(private val binding: DetailMealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindTo(
            item: Meals,
        ) {
            with(binding) {
                imgMealDetail.load(item.strMealThumb)
                tvAreaInfo.text = item.strArea
                tvCategoryInfo.text = item.strCategory
                tvInstructions.text = item.strInstructions
                collapsingToolbar.title = item.strMeal

                imgYoutube.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.strYoutube))
                    itemView.context.startActivity(intent)
                }
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
