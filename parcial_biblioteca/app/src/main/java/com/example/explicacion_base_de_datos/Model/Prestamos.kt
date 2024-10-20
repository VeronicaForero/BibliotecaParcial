package com.example.explicacion_base_de_datos.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "prestamos",
    foreignKeys = [
        ForeignKey(
            entity = Libros::class,
            parentColumns = ["libro_id"],
            childColumns = ["libro_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Miembros::class,
            parentColumns = ["miembro_id"],
            childColumns = ["miembro_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Prestamos(
    @PrimaryKey(autoGenerate = true)
    val prestamo_id: Int = 0,
    var libro_id: Int,
    var miembro_id: Int,
    var fecha_prestamo: String,
    var fecha_devolucion: String
)