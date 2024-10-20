package com.example.explicacion_base_de_datos.Repository

import com.example.explicacion_base_de_datos.Dao.PrestamosDao
import com.example.explicacion_base_de_datos.Model.Prestamos

class PrestamosRepository(private val prestamosDao: PrestamosDao) {
    suspend fun insert(prestamo: Prestamos) {
        prestamosDao.insertPrestamo(prestamo)
    }

    suspend fun getAll(): List<Prestamos> {
        return prestamosDao.getAllPrestamos()
    }

    suspend fun deleteById(id: Int) {
        prestamosDao.deletePrestamoById(id)
    }

    suspend fun getById(id: Int): Prestamos? {
        return prestamosDao.getPrestamoById(id)
    }

    suspend fun update(prestamo: Prestamos) {
        prestamosDao.updatePrestamo(prestamo)
    }
}