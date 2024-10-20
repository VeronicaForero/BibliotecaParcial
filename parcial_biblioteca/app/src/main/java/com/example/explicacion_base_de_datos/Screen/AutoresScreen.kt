package com.example.explicacion_base_de_datos.Screen
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.explicacion_base_de_datos.Model.Autores
import com.example.explicacion_base_de_datos.Repository.AutoresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AutoresApp(autoresRepository: AutoresRepository) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var nacionalidad by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var Borrar by remember { mutableStateOf("") }
    var Buscar by remember { mutableStateOf("") }

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = nacionalidad,
                onValueChange = { nacionalidad = it },
                label = { Text("Nacionalidad") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val autor = Autores(
                        nombre = nombre,
                        apellido = apellido,
                        nacionalidad = nacionalidad
                    )
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            autoresRepository.insert(autor)
                        }
                        Toast.makeText(context, "Autor Registrado", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            var autores by remember { mutableStateOf(listOf<Autores>()) }

            Button(
                onClick = {
                    scope.launch {
                        autores = withContext(Dispatchers.IO) {
                            autoresRepository.getAll()
                        }
                    }
                }
            ) {
                Text(text = "Listar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                autores.forEach { autor ->
                    Text("ID:${autor.autor_id} Nombre: ${autor.nombre} Apellido: ${autor.apellido} Nacionalidad: ${autor.nacionalidad}")
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
                            autoresRepository.deleteById(Borrar.toInt())
                        }
                        Toast.makeText(context, "Autor Borrado", Toast.LENGTH_SHORT).show()
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

            var Listar by remember { mutableStateOf(listOf<Autores>()) }

            Button(
                onClick = {
                    scope.launch {
                        Listar = withContext(Dispatchers.IO) {
                            listOfNotNull(autoresRepository.getById(Buscar.toInt()))
                        }
                    }
                }
            ) {
                Text(text = "Buscar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Listar.forEach { autor ->
                var nombre by remember { mutableStateOf(autor.nombre) }
                var apellido by remember { mutableStateOf(autor.apellido) }
                var nacionalidad by remember { mutableStateOf(autor.nacionalidad) }
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = apellido,
                    onValueChange = { apellido = it },
                    label = { Text("Apellido") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = nacionalidad,
                    onValueChange = { nacionalidad = it },
                    label = { Text("Nacionalidad") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                autoresRepository.update(Autores(autor.autor_id, nombre, apellido, nacionalidad))
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