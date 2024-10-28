package com.arthur.segura.pokeapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arthur.segura.pokeapp.data.local.datastore.PreferencesKeys
import com.arthur.segura.pokeapp.data.local.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesRepository @Inject constructor(private val context: Context) {

    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] ?: false
        }

    val viewMode: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.VIEW_MODE] ?: VIEW_LIST
        }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] = isDarkMode
        }
    }

    suspend fun setViewMode(viewMode: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.VIEW_MODE] = viewMode
        }
    }

    companion object {
        const val VIEW_LIST = "list"
        const val VIEW_GRID = "grid"
    }
}