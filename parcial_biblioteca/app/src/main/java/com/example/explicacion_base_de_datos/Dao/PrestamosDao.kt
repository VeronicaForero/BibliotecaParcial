package com.example.explicacion_base_de_datos.Dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.explicacion_base_de_datos.Model.Prestamos

@Dao
interface PrestamosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrestamo(prestamo: Prestamos)

    @Query("SELECT * FROM prestamos")
    suspend fun getAllPrestamos(): List<Prestamos>

    @Query("SELECT * FROM prestamos WHERE prestamo_id = :id")
    suspend fun getPrestamoById(id: Int): Prestamos?

    @Query("DELETE FROM prestamos WHERE prestamo_id = :id")
    suspend fun deletePrestamoById(id: Int)

    @Update
    suspend fun updatePrestamo(prestamo: Prestamos)
}