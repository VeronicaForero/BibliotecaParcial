package com.example.explicacion_base_de_datos.Repository
import com.example.explicacion_base_de_datos.Dao.AutoresDao
import com.example.explicacion_base_de_datos.Model.Autores

class AutoresRepository(private val autoresDao: AutoresDao) {
    suspend fun insert(autor: Autores) {
        autoresDao.insertAutor(autor)
    }

    suspend fun getAll(): List<Autores> {
        return autoresDao.getAllAutores()
    }

    suspend fun deleteById(id: Int) {
        autoresDao.deleteAutorById(id)
    }

    suspend fun getById(id: Int): Autores? {
        return autoresDao.getAutorById(id)
    }

    suspend fun update(autor: Autores) {
        autoresDao.updateAutor(autor)
    }
}