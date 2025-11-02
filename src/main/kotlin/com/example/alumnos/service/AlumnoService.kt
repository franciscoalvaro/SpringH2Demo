package com.example.alumnos.service

import com.example.alumnos.model.Alumno
import com.example.alumnos.repository.AlumnoRepository
import org.springframework.stereotype.Service

@Service
class AlumnoService(
    private val repo: AlumnoRepository
) {
    fun findAll(): List<Alumno> = repo.findAll()

    fun findById(id: Long): Alumno? = repo.findById(id).orElse(null)

    fun save(a: Alumno): Alumno = repo.save(a)

    fun delete(id: Long) = repo.deleteById(id)

    fun searchByNombre(q: String) = repo.findByNombreContainingIgnoreCase(q)

    fun searchByApellidos(q: String) = repo.findByApellidosContainingIgnoreCase(q)

    fun searchByCurso(curso: String) = repo.findByCursoIgnoreCase(curso)
}
