package com.example.cal_project2.data.remote.api

import com.example.cal_project2.model.RecipeModel
import retrofit2.http.GET

interface RecipesApi {

    @GET("recipes.json")
    suspend fun getRecipes(): List<RecipeModel>
}