package com.example.alumnos.controller

import com.example.alumnos.model.Alumno
import com.example.alumnos.service.AlumnoService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/alumnos")
class AlumnoController(
    private val service: AlumnoService
) {

    @GetMapping
    fun list(): List<Alumno> = service.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Alumno> {
        val found = service.findById(id)
        return if (found != null) ResponseEntity.ok(found) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@Valid @RequestBody alumno: Alumno): ResponseEntity<Alumno> {
        val saved = service.save(alumno)
        return ResponseEntity.created(URI.create("/api/alumnos/${saved.id}")).body(saved)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody alumno: Alumno): ResponseEntity<Alumno> {
        val existing = service.findById(id) ?: return ResponseEntity.notFound().build()

        existing.nombre = alumno.nombre
        existing.apellidos = alumno.apellidos
        existing.email = alumno.email
        existing.curso = alumno.curso
        existing.edad = alumno.edad
        existing.notaMedia = alumno.notaMedia

        return ResponseEntity.ok(service.save(existing))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val existing = service.findById(id) ?: return ResponseEntity.notFound().build()
        service.delete(existing.id!!)
        return ResponseEntity.noContent().build()
    }

    // Búsquedas
    @GetMapping("/buscar/nombre")
    fun buscarNombre(@RequestParam q: String) = service.searchByNombre(q)

    @GetMapping("/buscar/apellidos")
    fun buscarApellidos(@RequestParam q: String) = service.searchByApellidos(q)

    @GetMapping("/buscar/curso")
    fun buscarCurso(@RequestParam curso: String) = service.searchByCurso(curso)
}
