package com.arthur.segura.pokeapp.ui.views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.segura.pokeapp.presentation.viewmodels.PreferencesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeAppTopBar(
    viewModel: PreferencesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val isDarkTheme by viewModel.isDarkMode.collectAsState()

    TopAppBar(
        title = { Text("PokeApp") },
        modifier = modifier,
        actions = {
            IconButton(onClick = { viewModel.toggleDarkMode() }) {
                Text(if (isDarkTheme) "Light" else "Dark")
            }
        }
    )
}