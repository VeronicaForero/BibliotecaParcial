package com.example.explicacion_base_de_datos.Screen.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(
    navController: NavController,
    selectedScreen: Int,
    onScreenSelected: (Int) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedScreen == 0,
            onClick = {
                onScreenSelected(0)
                navController.navigate("autores")
            },
            icon = { Icon(Icons.Default.Person, contentDescription = "Autores Screen") },
            label = { Text("Autores") }
        )
        NavigationBarItem(
            selected = selectedScreen == 1,
            onClick = {
                onScreenSelected(1)
                navController.navigate("miembros")
            },
            icon = { Icon(Icons.Default.Person, contentDescription = "Miembros Screen") },
            label = { Text("Miembros") }
        )
        NavigationBarItem(
            selected = selectedScreen == 2,
            onClick = {
                onScreenSelected(2)
                navController.navigate("libros")
            },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Libros Screen") },
            label = { Text("Libros") }
        )
        NavigationBarItem(
            selected = selectedScreen == 3,
            onClick = {
                onScreenSelected(3)
                navController.navigate("prestamos")
            },
            icon = { Icon(Icons.Default.List, contentDescription = "Préstamos Screen") },
            label = { Text("Préstamos") }
        )
    }
}
