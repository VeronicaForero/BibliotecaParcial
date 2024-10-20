package com.example.explicacion_base_de_datos.Screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.explicacion_base_de_datos.Model.Autores
import com.example.explicacion_base_de_datos.Model.Libros
import com.example.explicacion_base_de_datos.Repository.AutoresRepository
import com.example.explicacion_base_de_datos.Repository.LibrosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LibrosScreen(librosRepository: LibrosRepository, autoresRepository: AutoresRepository) {
    var titulo by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var selectedAutorId by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var Borrar by remember { mutableStateOf("") }
    var Buscar by remember { mutableStateOf("") }

    val context = LocalContext.current

    var autores by remember { mutableStateOf(listOf<Autores>()) }

    LaunchedEffect(Unit) {
        scope.launch {
            autores = withContext(Dispatchers.IO) {
                autoresRepository.getAll()
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Titulo") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = genero,
                onValueChange = { genero = it },
                label = { Text("Genero") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = selectedAutorId,
                onValueChange = { selectedAutorId = it },
                label = { Text("Autor ID") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val libro = Libros(
                        titulo = titulo,
                        genero = genero,
                        autor_id = selectedAutorId.toIntOrNull() ?: 0
                    )
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            librosRepository.insert(libro)
                        }
                        Toast.makeText(context, "Libro Registrado", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            var libros by remember { mutableStateOf(listOf<Libros>()) }

            Button(
                onClick = {
                    scope.launch {
                        libros = withContext(Dispatchers.IO) {
                            librosRepository.getAll()
                        }
                    }
                }
            ) {
                Text(text = "Listar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                libros.forEach { libro ->
                    Text("ID:${libro.libro_id} Titulo: ${libro.titulo} Genero: ${libro.genero} Autor ID: ${libro.autor_id}")
                }
            }

            TextField(
                value = Borrar,
                onValueChange = { Borrar = it },
                label = { Text("Borrar") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            librosRepository.deleteById(Borrar.toInt())
                        }
                        Toast.makeText(context, "Libro Borrado", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Borrar")
            }
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = Buscar,
                onValueChange = { Buscar = it },
                label = { Text("ID BUSCAR") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            var Listar by remember { mutableStateOf(listOf<Libros>()) }

            Button(
                onClick = {
                    scope.launch {
                        Listar = withContext(Dispatchers.IO) {
                            listOfNotNull(librosRepository.getById(Buscar.toInt()))
                        }
                    }
                }
            ) {
                Text(text = "Buscar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Listar.forEach { libro ->
                var titulo by remember { mutableStateOf(libro.titulo) }
                var genero by remember { mutableStateOf(libro.genero) }
                var autorId by remember { mutableStateOf(libro.autor_id) }
                TextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Titulo") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Genero") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = autorId.toString(),
                    onValueChange = { libro.autor_id = it.toIntOrNull() ?: 0 },
                    label = { Text("Autor ID") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                librosRepository.insert(Libros(libro.libro_id, titulo, genero, autorId.toInt()))
                            }
                        }
                    }
                ) {
                    Text(text = "Actualizar")
                }
            }
        }
    }
}