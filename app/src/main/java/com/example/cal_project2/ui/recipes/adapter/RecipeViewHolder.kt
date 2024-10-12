package com.example.cal_project2.ui.recipes.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.cal_project2.databinding.ItemRecipeRowBinding
import com.example.cal_project2.model.RecipeModel
import com.example.cal_project2.ui.base.BaseViewHolder

class RecipeViewHolder(
    private var binding: ItemRecipeRowBinding,
    private val onItemClick: ((character: RecipeModel?) -> Unit)?
) : BaseViewHolder<RecipeModel>(binding.root) {

    override fun bind(recipe: RecipeModel?) {

        with(binding) {
            name.text = recipe?.name
            fats.text = recipe?.fats
            calories.text = recipe?.calories
            carbos.text = recipe?.carbos
        }
        Glide.with(binding.root)
            .load(recipe?.image)
            .transform(CircleCrop())
            .into(binding.thumb)

        itemView.setOnClickListener {
            recipe.let { onItemClick?.invoke(it) }
        }
    }
}