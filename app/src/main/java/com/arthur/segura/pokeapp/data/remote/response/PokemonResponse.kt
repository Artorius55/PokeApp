package com.arthur.segura.pokeapp.data.remote.response

import com.arthur.segura.pokeapp.data.remote.model.PokemonRemoteModel
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("previous")
    val prevPage: String?,
    @SerializedName("results")
    val results: List<PokemonRemoteModel>
)