package com.example.cal_project2.ui.recipe_details

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.cal_project2.databinding.FragmentRecipeDetailsBinding
import com.example.cal_project2.ui.base.BaseFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>(FragmentRecipeDetailsBinding::inflate){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().let {
            val args : RecipeDetailsFragmentArgs = RecipeDetailsFragmentArgs.fromBundle(it)
            showRecipeDetails(args)
        }

    }

    private fun showRecipeDetails(arg: RecipeDetailsFragmentArgs) {
        with(binding){
            name.text = arg.name
            calories.text = arg.calories
            carbos.text= arg.carbos
            descriptions.text = arg.description

            Glide.with(binding.root)
                .load(arg.image)
                .transform(CircleCrop())
                .into(binding.image)

        }
    }
}