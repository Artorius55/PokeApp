package com.arthur.segura.pokeapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.segura.pokeapp.domain.repository.PreferencesRepository
import com.arthur.segura.pokeapp.presentation.states.UiState
import com.arthur.segura.pokeapp.presentation.viewmodels.PokemonViewModel


@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val viewMode by viewModel.viewMode.collectAsState(initial = PreferencesRepository.VIEW_LIST)

    when (uiState) {
        is UiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()  // Muestra el loader circular
            }
        }
        is UiState.Success -> {
            val pokemons = (uiState as UiState.Success).data

            Box(modifier = modifier.fillMaxSize()) {
                Button(
                    onClick = { viewModel.toggleViewMode() },
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Text("Switch to ${if (viewMode == PreferencesRepository.VIEW_LIST) "Grid" else "List"}")
                }

                PokemonList(
                    pokemons = pokemons,
                    viewMode = viewMode,
                    modifier = modifier
                )
            }
        }
        is UiState.Error -> {
            val message = (uiState as UiState.Error).message
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: $message")
            }
        }
    }
}

