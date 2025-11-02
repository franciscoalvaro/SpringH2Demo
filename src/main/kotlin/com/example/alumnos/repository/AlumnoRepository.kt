package com.example.alumnos.repository

import com.example.alumnos.model.Alumno
import org.springframework.data.jpa.repository.JpaRepository

interface AlumnoRepository : JpaRepository<Alumno, Long> {
    fun findByNombreContainingIgnoreCase(nombre: String): List<Alumno>
    fun findByApellidosContainingIgnoreCase(apellidos: String): List<Alumno>
    fun findByCursoIgnoreCase(curso: String): List<Alumno>
}
