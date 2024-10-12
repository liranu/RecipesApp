package com.example.cal_project2.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cal_project2.model.RecipeModel
import com.example.cal_project2.repository.RecipesRepository
import com.example.cal_project2.sealed.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    val recipes: StateFlow<Result<List<RecipeModel>>> = flow {
        recipesRepository.getRecipes().collect { emit(it) }
    }.stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)
}
