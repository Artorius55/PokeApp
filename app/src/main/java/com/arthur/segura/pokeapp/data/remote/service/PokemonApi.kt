package com.arthur.segura.pokeapp.data.remote.service

import com.arthur.segura.pokeapp.data.remote.response.PokemonResponse
import retrofit2.http.GET

interface PokemonApi {
    @GET ("pokemon")
    suspend fun getPokemonList(): PokemonResponse
}
