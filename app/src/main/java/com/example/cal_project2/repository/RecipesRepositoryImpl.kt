package com.example.cal_project2.repository

import com.example.cal_project2.data.remote.api.RecipesApi
import com.example.cal_project2.model.RecipeModel
import com.example.cal_project2.sealed.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesApi: RecipesApi
) : RecipesRepository {


    override suspend fun getRecipes(): Flow<Result<List<RecipeModel>>> = flow {
        emit(Result.Loading)
        try {
            val items = recipesApi.getRecipes()
            emit(Result.Success(items))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}