package com.example.appfood.ui.filter_category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appfood.databinding.MealCategoryFilterItemBinding
import com.example.core.data.source.remote.response.Meal

class CategoryFilterAdapter
    : ListAdapter<Meal, CategoryFilterAdapter.CategoryFilterViewHolder>(DiffCallback()) {

    lateinit var onButtonDetailClick: ((Meal) -> Unit)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryFilterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            MealCategoryFilterItemBinding.inflate(inflater, parent, false)
        return CategoryFilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryFilterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item)


    }

    inner class CategoryFilterViewHolder(private val binding: MealCategoryFilterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindTo(
            item: Meal,
        ) {

            with(binding) {
                tvMeal.text= item.strMeal
                imgSearchedMeal.load(item.strMealThumb)
                tvSeeDetail.setOnClickListener {
                    onButtonDetailClick.invoke(item)
                }
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}
