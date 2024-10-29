package com.arthur.segura.pokeapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val isDarkMode: Flow<Boolean>
    val viewMode: Flow<String>

    suspend fun setDarkMode(isDarkMode: Boolean)
    suspend fun setViewMode(viewMode: String)

    companion object {
        const val VIEW_LIST = "list"
        const val VIEW_GRID = "grid"
    }
}