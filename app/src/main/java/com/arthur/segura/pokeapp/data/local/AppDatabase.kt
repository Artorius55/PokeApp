package com.arthur.segura.pokeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arthur.segura.pokeapp.data.local.dao.PokemonDao
import com.arthur.segura.pokeapp.data.local.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}