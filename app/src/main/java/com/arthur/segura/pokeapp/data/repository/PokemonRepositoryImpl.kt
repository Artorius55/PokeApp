package com.arthur.segura.pokeapp.data.repository

import com.arthur.segura.pokeapp.data.local.dao.PokemonDao
import com.arthur.segura.pokeapp.data.local.model.PokemonEntity
import com.arthur.segura.pokeapp.data.remote.service.PokemonApi
import com.arthur.segura.pokeapp.domain.model.Pokemon
import com.arthur.segura.pokeapp.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
    private val pokemonDao: PokemonDao
) : PokemonRepository {
    override suspend fun getPokemons(): Result<List<Pokemon>> {
        return try {
            // Obtener los Pokémon de la base de datos
            val localData = pokemonDao.getAllPokemons()
            if (localData.isNotEmpty()) {
                // Si hay datos en la BD, devolverlos
                Result.success(
                    localData.map {
                        Pokemon(it.id, it.name)
                    }
                )
            } else {
                val response = api.getPokemonList()
                val pokemonEntities = response.results.map { remoteModel ->
                    PokemonEntity(
                        id = remoteModel.id,
                        name = remoteModel.name
                    )
                }
                val pokemons = response.results.map { remoteModel ->
                    Pokemon(
                        id = remoteModel.id,
                        name = remoteModel.name
                    )
                }
                // Guardar los datos en la base de datos
                pokemonDao.insertAll(*pokemonEntities.toTypedArray())

                Result.success(pokemons)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}