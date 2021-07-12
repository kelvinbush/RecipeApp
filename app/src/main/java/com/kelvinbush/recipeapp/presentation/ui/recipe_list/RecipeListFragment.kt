package com.kelvinbush.recipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.kelvinbush.recipeapp.R
import com.kelvinbush.recipeapp.presentation.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "RecipeListFragment"

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                Column {
                    TextField(value = query, onValueChange = { newValue ->
                        viewModel.onQueryChanged(newValue)
                    })
                    Spacer(modifier = Modifier.padding(10.dp))
                    LazyColumn {
                        itemsIndexed(items = recipes) { index, item ->
                            RecipeCard(recipe = item, onClick = {})
                        }
                    }
                }
            }
        }
    }

}