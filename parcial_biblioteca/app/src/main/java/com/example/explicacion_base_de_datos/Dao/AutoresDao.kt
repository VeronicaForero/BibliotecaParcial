package com.example.explicacion_base_de_datos.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.example.explicacion_base_de_datos.Model.Autores

@Dao
interface AutoresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAutor(autor: Autores)

    @Query("SELECT * FROM autores")
    suspend fun getAllAutores(): List<Autores>

    @Query("SELECT * FROM autores WHERE autor_id = :id")
    suspend fun getAutorById(id: Int): Autores?

    @Query("DELETE FROM autores WHERE autor_id = :id")
    suspend fun deleteAutorById(id: Int)

    @Update
    suspend fun updateAutor(autor: Autores)
}