package com.arthur.segura.pokeapp.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.segura.pokeapp.presentation.viewmodels.PreferencesViewModel
import com.arthur.segura.pokeapp.ui.theme.PokeAppTheme
import com.arthur.segura.pokeapp.ui.views.PokeAppTopBar
import com.arthur.segura.pokeapp.ui.views.PokemonListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val preferencesViewModel = hiltViewModel<PreferencesViewModel>()
            val isDarkTheme by preferencesViewModel.isDarkMode.collectAsState()

            PokeAppTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    topBar = { PokeAppTopBar() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    PokemonListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}