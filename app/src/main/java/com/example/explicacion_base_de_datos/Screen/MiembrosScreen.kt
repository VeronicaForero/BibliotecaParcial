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
import com.example.explicacion_base_de_datos.Model.Miembros
import com.example.explicacion_base_de_datos.Repository.MiembrosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MiembrosScreen(miembrosRepository: MiembrosRepository) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var fechaInscripcion by remember { mutableStateOf("") }
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
                value = fechaInscripcion,
                onValueChange = { fechaInscripcion = it },
                label = { Text("Fecha de Inscripción") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val miembro = Miembros(
                        nombre = nombre,
                        apellido = apellido,
                        fecha_inscripcion = fechaInscripcion
                    )
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            miembrosRepository.insert(miembro)
                        }
                        Toast.makeText(context, "Miembro Registrado", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            var miembros by remember { mutableStateOf(listOf<Miembros>()) }

            Button(
                onClick = {
                    scope.launch {
                        miembros = withContext(Dispatchers.IO) {
                            miembrosRepository.getAll()
                        }
                    }
                }
            ) {
                Text(text = "Listar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                miembros.forEach { miembro ->
                    Text("ID:${miembro.miembro_id} Nombre: ${miembro.nombre} Apellido: ${miembro.apellido} Fecha de Inscripción: ${miembro.fecha_inscripcion}")
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
                            miembrosRepository.deleteById(Borrar.toInt())
                        }
                        Toast.makeText(context, "Miembro Borrado", Toast.LENGTH_SHORT).show()
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

            var Listar by remember { mutableStateOf(listOf<Miembros>()) }

            Button(
                onClick = {
                    scope.launch {
                        Listar = withContext(Dispatchers.IO) {
                            listOfNotNull(miembrosRepository.getById(Buscar.toInt()))
                        }
                    }
                }
            ) {
                Text(text = "Buscar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Listar.forEach { miembro ->
                var nombre by remember { mutableStateOf(miembro.nombre) }
                var apellido by remember { mutableStateOf(miembro.apellido) }
                var fechaInscripcion by remember { mutableStateOf(miembro.fecha_inscripcion) }
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
                    value = fechaInscripcion,
                    onValueChange = { fechaInscripcion = it },
                    label = { Text("Fecha de Inscripción") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                miembrosRepository.insert(Miembros(miembro.miembro_id, nombre, apellido, fechaInscripcion))
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