package com.arthur.segura.pokeapp.presentation.states

import com.arthur.segura.pokeapp.domain.model.Pokemon

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Pokemon>) : UiState()
    data class Error(val message: String) : UiState()
}