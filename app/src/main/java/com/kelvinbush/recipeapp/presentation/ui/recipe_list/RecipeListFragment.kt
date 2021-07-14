package com.kelvinbush.recipeapp.presentation.ui.recipe_list

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kelvinbush.recipeapp.presentation.BaseApplication
import com.kelvinbush.recipeapp.presentation.components.CircularIndeterminateProgressBar
import com.kelvinbush.recipeapp.presentation.components.FoodCategoryChip
import com.kelvinbush.recipeapp.presentation.components.RecipeCard
import com.kelvinbush.recipeapp.presentation.components.SearchAppBar
import com.kelvinbush.recipeapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RecipeListFragment"

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireActivity().application as BaseApplication

        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(darkTheme = application.isDark.value) {
                    val recipes = viewModel.recipes.value
                    val query = viewModel.query.value
                    val loading = viewModel.loading.value

                    val selectedCategory = viewModel.selectedCategory.value
                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = viewModel::newSearch,
                                scrollPosition = viewModel.categoryScrollPosition,
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                                onToggleTheme = { application.toggleLightTheme() }
                            )
                        },
                        bottomBar = { myBottomBar() },
                        drawerContent = {},
                    ) {
                        Box(
                            modifier = Modifier
                                .background(color = MaterialTheme.colors.surface)
                                .fillMaxSize()
                        ) {
                            LazyColumn {
                                itemsIndexed(items = recipes) { _, item ->
                                    RecipeCard(recipe = item, onClick = {})
                                }
                            }
                            CircularIndeterminateProgressBar(isDisplayed = loading)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun myBottomBar() {
    BottomNavigation(
        elevation = 12.dp
    ) {
        BottomNavigationItem(selected = false, onClick = {}, icon = {
            Icon(
                Icons.Default.ThumbUp,
                contentDescription = ""
            )
        })
        BottomNavigationItem(selected = false, onClick = {}, icon = {
            Icon(
                Icons.Default.Search,
                contentDescription = ""
            )
        })
        BottomNavigationItem(selected = true, onClick = {}, icon = {
            Icon(
                Icons.Default.AccountBox,
                contentDescription = ""
            )
        })
    }
}