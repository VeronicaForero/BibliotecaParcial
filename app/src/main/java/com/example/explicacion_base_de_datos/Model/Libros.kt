package com.example.explicacion_base_de_datos.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "libros",
    foreignKeys = [ForeignKey(
        entity = Autores::class,
        parentColumns = ["autor_id"],
        childColumns = ["autor_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Libros(
    @PrimaryKey(autoGenerate = true)
    val libro_id: Int = 0,
    val titulo: String,
    val genero: String,
    var autor_id: Int
)