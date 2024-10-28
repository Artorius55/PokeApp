package com.arthur.segura.pokeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arthur.segura.pokeapp.data.local.model.PokemonEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    suspend fun getAllPokemons(): List<PokemonEntity>

    @Insert
    suspend fun insertAll(vararg pokemons: PokemonEntity)
}