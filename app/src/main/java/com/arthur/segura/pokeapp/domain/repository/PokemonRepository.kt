package com.arthur.segura.pokeapp.domain.repository

import com.arthur.segura.pokeapp.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(): Result<List<Pokemon>>
}