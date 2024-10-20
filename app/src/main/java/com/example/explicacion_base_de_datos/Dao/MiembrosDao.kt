package com.example.explicacion_base_de_datos.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.example.explicacion_base_de_datos.Model.Miembros

@Dao
interface MiembrosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMiembro(miembro: Miembros)

    @Query("SELECT * FROM miembros")
    suspend fun getAllMiembros(): List<Miembros>

    @Query("SELECT * FROM miembros WHERE miembro_id = :id")
    suspend fun getMiembroById(id: Int): Miembros?

    @Query("DELETE FROM miembros WHERE miembro_id = :id")
    suspend fun deleteMiembroById(id: Int)

    @Update
    suspend fun updateMiembro(miembro: Miembros)
}