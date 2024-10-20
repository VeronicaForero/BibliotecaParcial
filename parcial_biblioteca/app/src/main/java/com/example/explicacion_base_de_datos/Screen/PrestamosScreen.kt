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
import com.example.explicacion_base_de_datos.Model.Libros
import com.example.explicacion_base_de_datos.Model.Miembros
import com.example.explicacion_base_de_datos.Model.Prestamos
import com.example.explicacion_base_de_datos.Repository.LibrosRepository
import com.example.explicacion_base_de_datos.Repository.MiembrosRepository
import com.example.explicacion_base_de_datos.Repository.PrestamosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PrestamosScreen(
    prestamosRepository: PrestamosRepository,
    librosRepository: LibrosRepository,
    miembrosRepository: MiembrosRepository
) {
    var selectedLibroId by remember { mutableStateOf("") }
    var selectedMiembroId by remember { mutableStateOf("") }
    var fechaPrestamo by remember { mutableStateOf("") }
    var fechaDevolucion by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var Borrar by remember { mutableStateOf("") }
    var Buscar by remember { mutableStateOf("") }

    val context = LocalContext.current

    var libros by remember { mutableStateOf(listOf<Libros>()) }
    var miembros by remember { mutableStateOf(listOf<Miembros>()) }

    LaunchedEffect(Unit) {
        scope.launch {
            libros = withContext(Dispatchers.IO) {
                librosRepository.getAll()
            }
            miembros = withContext(Dispatchers.IO) {
                miembrosRepository.getAll()
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
                value = selectedLibroId,
                onValueChange = { selectedLibroId = it },
                label = { Text("Libro ID") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = selectedMiembroId,
                onValueChange = { selectedMiembroId = it },
                label = { Text("Miembro ID") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = fechaPrestamo,
                onValueChange = { fechaPrestamo = it },
                label = { Text("Fecha de Préstamo") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = fechaDevolucion,
                onValueChange = { fechaDevolucion = it },
                label = { Text("Fecha de Devolución") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val prestamo = Prestamos(
                        libro_id = selectedLibroId.toIntOrNull() ?: 0,
                        miembro_id = selectedMiembroId.toIntOrNull() ?: 0,
                        fecha_prestamo = fechaPrestamo,
                        fecha_devolucion = fechaDevolucion
                    )
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            prestamosRepository.insert(prestamo)
                        }
                        Toast.makeText(context, "Préstamo Registrado", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            var prestamos by remember { mutableStateOf(listOf<Prestamos>()) }

            Button(
                onClick = {
                    scope.launch {
                        prestamos = withContext(Dispatchers.IO) {
                            prestamosRepository.getAll()
                        }
                    }
                }
            ) {
                Text(text = "Listar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                prestamos.forEach { prestamo ->
                    Text("ID:${prestamo.prestamo_id} Libro ID: ${prestamo.libro_id} Miembro ID: ${prestamo.miembro_id} Fecha de Préstamo: ${prestamo.fecha_prestamo} Fecha de Devolución: ${prestamo.fecha_devolucion}")
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
                            prestamosRepository.deleteById(Borrar.toInt())
                        }
                        Toast.makeText(context, "Préstamo Borrado", Toast.LENGTH_SHORT).show()
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

            var Listar by remember { mutableStateOf(listOf<Prestamos>()) }

            Button(
                onClick = {
                    scope.launch {
                        Listar = withContext(Dispatchers.IO) {
                            listOfNotNull(prestamosRepository.getById(Buscar.toInt()))
                        }
                    }
                }
            ) {
                Text(text = "Buscar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Listar.forEach { prestamo ->
                var libroId by remember { mutableStateOf(prestamo.libro_id.toString()) }
                var miembroId by remember { mutableStateOf(prestamo.miembro_id.toString()) }
                var fechaPrestamo by remember { mutableStateOf(prestamo.fecha_prestamo) }
                var fechaDevolucion by remember { mutableStateOf(prestamo.fecha_devolucion) }

                TextField(
                    value = libroId.toString(),
                    onValueChange = { libroId = (it.toIntOrNull() ?: 0).toString() },
                    label = { Text("Libro ID") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = miembroId.toString(),
                    onValueChange = { miembroId = (it.toIntOrNull() ?: 0).toString() },
                    label = { Text("Miembro ID") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = fechaPrestamo,
                    onValueChange = { fechaPrestamo = it },
                    label = { Text("Fecha de Préstamo") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = fechaDevolucion,
                    onValueChange = { fechaDevolucion = it },
                    label = { Text("Fecha de Devolución") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                prestamosRepository.insert(Prestamos(prestamo.prestamo_id, libroId.toInt(), miembroId.toInt(), fechaPrestamo, fechaDevolucion))
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