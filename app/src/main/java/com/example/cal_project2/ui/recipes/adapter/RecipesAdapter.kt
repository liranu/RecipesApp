package com.example.cal_project2.ui.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cal_project2.databinding.ItemRecipeRowBinding
import com.example.cal_project2.model.RecipeModel
import com.example.cal_project2.ui.base.BaseViewHolder
import javax.inject.Inject

class RecipesAdapter @Inject constructor(
    private var recipes: List<RecipeModel>
) : RecyclerView.Adapter<BaseViewHolder<RecipeModel>>() {

    var onItemClick: ((item: RecipeModel?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecipeModel> {
        val binding = ItemRecipeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding, onItemClick)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: BaseViewHolder<RecipeModel>, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    fun updateItems(newItems: List<RecipeModel>) {
        recipes = newItems
        notifyDataSetChanged()
    }

}
