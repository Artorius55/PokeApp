package com.arthur.segura.pokeapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.segura.pokeapp.presentation.states.UiState
import com.arthur.segura.pokeapp.presentation.viewmodels.PokemonViewModel


@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
            LazyColumn(modifier = modifier) {
                items(pokemons) { pokemon ->
                    Text(text = pokemon.name)
                }
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

