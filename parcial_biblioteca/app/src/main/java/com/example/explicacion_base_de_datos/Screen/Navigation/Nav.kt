package com.example.explicacion_base_de_datos.Screen.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.explicacion_base_de_datos.Repository.*
import com.example.explicacion_base_de_datos.Screen.AutoresApp
import com.example.explicacion_base_de_datos.Screen.LibrosScreen
import com.example.explicacion_base_de_datos.Screen.MiembrosScreen
import com.example.explicacion_base_de_datos.Screen.PrestamosScreen

@Composable
fun MainScreen(
    autoresRepository: AutoresRepository,
    miembrosRepository: MiembrosRepository,
    librosRepository: LibrosRepository,
    prestamosRepository: PrestamosRepository
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "autores") {
        composable("autores") { AutoresApp(autoresRepository) }
        composable("miembros") { MiembrosScreen(miembrosRepository) }
        composable("libros") { LibrosScreen(librosRepository, autoresRepository) }
        composable("prestamos") { PrestamosScreen(prestamosRepository, librosRepository, miembrosRepository) }
    }
}