package com.arthur.segura.pokeapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.segura.pokeapp.domain.repository.PokemonRepository
import com.arthur.segura.pokeapp.presentation.states.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> get() = _uiState

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        _uiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)
            try {
                val result = repository.getPokemons()
                if (result.isSuccess) {
                    _uiState.value = UiState.Success(result.getOrThrow())
                } else {
                    _uiState.value = UiState.Error("Error fetching data")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}