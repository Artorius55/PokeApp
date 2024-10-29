package com.arthur.segura.pokeapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.segura.pokeapp.domain.repository.PreferencesRepository
import com.arthur.segura.pokeapp.domain.repository.PreferencesRepository.Companion.VIEW_GRID
import com.arthur.segura.pokeapp.domain.repository.PreferencesRepository.Companion.VIEW_LIST
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> get() = _isDarkMode

    private val _viewMode = MutableStateFlow("list")
    val viewMode: StateFlow<String> get() = _viewMode

    init {
        viewModelScope.launch {
            preferencesRepository.isDarkMode.collect { isDark ->
                _isDarkMode.value = isDark
            }
        }
        viewModelScope.launch {
            preferencesRepository.viewMode.collect { mode ->
                _viewMode.value = mode
            }
        }
    }

    fun toggleDarkMode() {
        viewModelScope.launch {
            preferencesRepository.setDarkMode(!_isDarkMode.value)
        }
    }

    fun changeViewMode() {
        viewModelScope.launch {
            val newMode = if (_viewMode.value == VIEW_LIST) VIEW_GRID else VIEW_LIST
            preferencesRepository.setViewMode(newMode)
        }
    }
}