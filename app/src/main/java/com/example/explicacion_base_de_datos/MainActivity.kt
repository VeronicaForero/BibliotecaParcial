package com.example.explicacion_base_de_datos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.explicacion_base_de_datos.Database.BibliotecaDatabase
import com.example.explicacion_base_de_datos.Repository.AutoresRepository
import com.example.explicacion_base_de_datos.Repository.LibrosRepository
import com.example.explicacion_base_de_datos.Repository.MiembrosRepository
import com.example.explicacion_base_de_datos.Repository.PrestamosRepository
import com.example.explicacion_base_de_datos.Screen.UserApp

class MainActivity : ComponentActivity() {


    private lateinit var autoresRepository: AutoresRepository
    private lateinit var miembrosRepository: MiembrosRepository
    private lateinit var librosRepository: LibrosRepository
    private lateinit var prestamosRepository: PrestamosRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = BibliotecaDatabase.getDatabase(applicationContext)


        autoresRepository = AutoresRepository(db.autoresDao())
        miembrosRepository = MiembrosRepository(db.miembrosDao())
        librosRepository = LibrosRepository(db.librosDao())
        prestamosRepository = PrestamosRepository(db.prestamosDao())

        enableEdgeToEdge()
        setContent {
            UserApp(
                autoresRepository = autoresRepository,
                miembrosRepository = miembrosRepository,
                librosRepository = librosRepository,
                prestamosRepository = prestamosRepository
            )
        }
    }
}

