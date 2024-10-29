package com.arthur.segura.pokeapp.application.di

import android.content.Context
import com.arthur.segura.pokeapp.data.local.dao.PokemonDao
import com.arthur.segura.pokeapp.data.remote.service.PokemonApi
import com.arthur.segura.pokeapp.data.repository.PokemonRepositoryImpl
import com.arthur.segura.pokeapp.data.repository.PreferencesRepositoryImpl
import com.arthur.segura.pokeapp.domain.repository.PokemonRepository
import com.arthur.segura.pokeapp.domain.repository.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providePokemonRepository(
        api: PokemonApi,
        pokemonDao: PokemonDao
    ): PokemonRepository {
        return PokemonRepositoryImpl(api, pokemonDao)
    }

    @Provides
    fun providePreferencesRepository(
        @ApplicationContext context: Context
    ): PreferencesRepository {
        return PreferencesRepositoryImpl(context)
    }
}