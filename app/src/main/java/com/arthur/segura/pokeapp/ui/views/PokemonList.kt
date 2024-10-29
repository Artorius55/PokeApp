package com.arthur.segura.pokeapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arthur.segura.pokeapp.domain.model.Pokemon
import com.arthur.segura.pokeapp.domain.repository.PreferencesRepository

@Composable
fun PokemonList(
    pokemons: List<Pokemon>,
    viewMode: String,
    modifier: Modifier = Modifier
) {
    if (viewMode == PreferencesRepository.VIEW_LIST) {
        // Mostrar en formato de lista
        LazyColumn(modifier = modifier) {
            items(pokemons) { pokemon ->
                PokemonItem(
                    id = pokemon.id,
                    name = pokemon.name
                )
            }
        }
    } else {
        // Mostrar en formato de grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
        ) {
            items(pokemons) { pokemon ->
                PokemonItem(
                    id = pokemon.id,
                    name = pokemon.name
                )
            }
        }
    }
}

@Composable
fun PokemonItem(
    id: Int,
    name: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "ID: $id",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontSize = 16.sp
            )
        }
    }
}