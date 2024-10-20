package com.example.explicacion_base_de_datos.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "autores")
data class Autores(
    @PrimaryKey(autoGenerate = true)
    val autor_id: Int = 0,
    val nombre: String,
    val apellido: String,
    val nacionalidad: String
)