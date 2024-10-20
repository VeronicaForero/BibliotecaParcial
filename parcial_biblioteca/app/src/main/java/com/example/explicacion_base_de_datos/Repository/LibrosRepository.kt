package com.example.explicacion_base_de_datos.Repository

import com.example.explicacion_base_de_datos.Dao.LibrosDao
import com.example.explicacion_base_de_datos.Model.Libros

class LibrosRepository(private val librosDao: LibrosDao) {
    suspend fun insert(libro: Libros) {
        librosDao.insertLibro(libro)
    }

    suspend fun getAll(): List<Libros> {
        return librosDao.getAllLibros()
    }

    suspend fun deleteById(id: Int) {
        librosDao.deleteLibroById(id)
    }

    suspend fun getById(id: Int): Libros? {
        return librosDao.getLibroById(id)
    }

    suspend fun update(libro: Libros) {
        librosDao.updateLibro(libro)
    }
}