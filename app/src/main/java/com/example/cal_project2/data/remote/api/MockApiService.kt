package com.example.cal_project2.data.remote.api

import android.content.Context
import com.example.cal_project2.model.RecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MockApiService(private val context: Context) : RecipesApi {

    override suspend fun getRecipes(): List<RecipeModel> {
        // Load the JSON from assets
        val jsonString = loadJsonFromAssets("recipes.json")
        return Gson().fromJson(jsonString, object : TypeToken<List<RecipeModel>>() {}.type)
    }

    private fun loadJsonFromAssets(filename: String): String {
        return context.assets.open(filename).bufferedReader().use { it.readText() }
    }
}