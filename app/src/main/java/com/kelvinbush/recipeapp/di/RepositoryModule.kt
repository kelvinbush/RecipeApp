package com.kelvinbush.recipeapp.di

import com.kelvinbush.recipeapp.network.RecipeService
import com.kelvinbush.recipeapp.network.model.RecipeDtoMapper
import com.kelvinbush.recipeapp.repository.RecipeRepository
import com.kelvinbush.recipeapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(recipeService, recipeDtoMapper)
    }
}