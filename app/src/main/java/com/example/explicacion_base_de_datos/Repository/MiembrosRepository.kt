package com.example.explicacion_base_de_datos.Repository

import com.example.explicacion_base_de_datos.Dao.MiembrosDao
import com.example.explicacion_base_de_datos.Model.Libros
import com.example.explicacion_base_de_datos.Model.Miembros

class MiembrosRepository(private val miembrosDao: MiembrosDao) {
    suspend fun insert(miembro: Miembros) {
        miembrosDao.insertMiembro(miembro)
    }

    suspend fun getAll(): List<Miembros> {
        return miembrosDao.getAllMiembros()
    }

    suspend fun deleteById(id: Int) {
        miembrosDao.deleteMiembroById(id)
    }

    suspend fun getById(id: Int): Miembros? {
        return miembrosDao.getMiembroById(id)
    }

    suspend fun update(miembro: Miembros) {
        miembrosDao.updateMiembro(miembro)
    }
}