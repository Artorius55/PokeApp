package com.arthur.segura.pokeapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arthur.segura.pokeapp.data.local.datastore.PreferencesKeys
import com.arthur.segura.pokeapp.data.local.datastore.dataStore
import com.arthur.segura.pokeapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val context: Context
) : PreferencesRepository {

    override val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] ?: false
        }

    override val viewMode: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.VIEW_MODE] ?: PreferencesRepository.VIEW_LIST
        }

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] = isDarkMode
        }
    }

    override suspend fun setViewMode(viewMode: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.VIEW_MODE] = viewMode
        }
    }
}