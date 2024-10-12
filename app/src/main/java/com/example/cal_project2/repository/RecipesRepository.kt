package com.example.cal_project2.repository

import com.example.cal_project2.model.RecipeModel
import com.example.cal_project2.sealed.Result
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun getRecipes() : Flow<Result<List<RecipeModel>>>
}