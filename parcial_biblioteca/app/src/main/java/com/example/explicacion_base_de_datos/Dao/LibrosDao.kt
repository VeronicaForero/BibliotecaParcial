package com.example.explicacion_base_de_datos.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.example.explicacion_base_de_datos.Model.Libros


@Dao
interface LibrosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLibro(libro: Libros)

    @Query("SELECT * FROM libros")
    suspend fun getAllLibros(): List<Libros>

    @Query("SELECT * FROM libros WHERE libro_id = :id")
    suspend fun getLibroById(id: Int): Libros?

    @Query("DELETE FROM libros WHERE libro_id = :id")
    suspend fun deleteLibroById(id: Int)

    @Update
    suspend fun updateLibro(libro: Libros)
}