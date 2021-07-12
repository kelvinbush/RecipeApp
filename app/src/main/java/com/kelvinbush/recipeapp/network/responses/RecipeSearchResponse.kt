package com.kelvinbush.recipeapp.network.responses

import com.google.gson.annotations.SerializedName
import com.kelvinbush.recipeapp.network.model.RecipeDto

class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("results")
    var recipes: List<RecipeDto>
)