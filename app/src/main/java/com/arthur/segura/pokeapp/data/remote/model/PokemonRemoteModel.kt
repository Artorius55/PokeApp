package com.arthur.segura.pokeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonRemoteModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) {
    val id: Int
        get() = url
            .trimEnd('/')
            .substringAfterLast('/')
            .toInt()
}