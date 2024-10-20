package com.example.explicacion_base_de_datos.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.explicacion_base_de_datos.Dao.*
import com.example.explicacion_base_de_datos.Model.*

@Database(entities = [Libros::class, Autores::class, Prestamos::class, Miembros::class], version = 1, exportSchema = false)
abstract class BibliotecaDatabase : RoomDatabase() {
    abstract fun librosDao(): LibrosDao
    abstract fun autoresDao(): AutoresDao
    abstract fun prestamosDao(): PrestamosDao
    abstract fun miembrosDao(): MiembrosDao

    companion object {
        @Volatile
        private var INSTANCE: BibliotecaDatabase? = null

        fun getDatabase(context: Context): BibliotecaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BibliotecaDatabase::class.java,
                    "biblioteca_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}