package com.example.explicacion_base_de_datos.Screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.explicacion_base_de_datos.Repository.AutoresRepository
import com.example.explicacion_base_de_datos.Repository.LibrosRepository
import com.example.explicacion_base_de_datos.Repository.MiembrosRepository
import com.example.explicacion_base_de_datos.Repository.PrestamosRepository
import com.example.explicacion_base_de_datos.Screen.Navigation.BottomNavigationBar

@Composable
fun UserApp(
    autoresRepository: AutoresRepository,
    miembrosRepository: MiembrosRepository,
    librosRepository: LibrosRepository,
    prestamosRepository: PrestamosRepository
) {
    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedScreen = selectedScreen,
                onScreenSelected = { selectedScreen = it }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "autores",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("autores") { AutoresApp(autoresRepository) }
            composable("miembros") { MiembrosScreen(miembrosRepository) }
            composable("libros") { LibrosScreen(librosRepository, autoresRepository) }
            composable("prestamos") { PrestamosScreen(prestamosRepository, librosRepository, miembrosRepository) }

        }
    }
}
