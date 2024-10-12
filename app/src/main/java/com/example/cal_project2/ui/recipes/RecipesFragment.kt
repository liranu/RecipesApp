package com.example.cal_project2.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cal_project2.databinding.FragmentRecipesBinding
import com.example.cal_project2.model.RecipeModel
import com.example.cal_project2.sealed.Result
import com.example.cal_project2.ui.base.BaseFragment
import com.example.cal_project2.ui.recipes.adapter.RecipesAdapter
import com.example.cal_project2.utils.ViewExtentions.remove
import com.example.cal_project2.utils.ViewExtentions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : BaseFragment<FragmentRecipesBinding>(FragmentRecipesBinding::inflate) {

    private lateinit var recipesAdapter: RecipesAdapter
    private val recipesViewModel: RecipesViewModel by viewModels()
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
        setupJob()
    }

    private fun setupJob() {
        job?.cancel()
        job = lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                recipesViewModel.recipes.collect { result ->
                    when (result) {
                        is Result.Loading ->
                            binding.progressBar.show()
                        
                        is Result.Success -> {
                            recipesAdapter.updateItems(result.data)
                            binding.progressBar.remove()
                        }

                        is Result.Error -> {
                            binding.progressBar.remove()
                            Toast.makeText(
                                requireActivity(),
                                result.exception.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() = with(binding.recipesRecyclerView) {
        recipesAdapter = RecipesAdapter(emptyList())
        recipesAdapter.onItemClick = { onItemClickListener(it) }
        adapter = recipesAdapter
        layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun onItemClickListener(recipeModel: RecipeModel?) {
        recipeModel?.let { recipe ->
            authenticateAndShowDetails(recipe)
        }

    }

    private fun authenticateAndShowDetails(recipe: RecipeModel) {
        val biometricPrompt = BiometricPrompt(this,
            ContextCompat.getMainExecutor(requireActivity()),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    val direction =
                        RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(
                            recipe.name,
                            recipe.image,
                            recipe.fats,
                            recipe.calories,
                            recipe.carbos,
                            recipe.description
                        )
                    findNavController().navigate(direction)

                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Toast.makeText(
                        requireContext(),
                        "Authentication error: $errString",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationFailed() {
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}
