package com.example.alumnos.model

import jakarta.persistence.*
import jakarta.validation.constraints.*

/**
 * IMPORTANTE en Kotlin + JPA:
 * - Usar 'open' no es necesario por el plugin kotlin-jpa (genera no-arg y abre clases/properties para proxys).
 * - Usar 'var' y no 'val' para que Hibernate pueda settear.
 * - El id debe ser nullable y con valor por defecto null.
 */
@Entity
@Table(name = "alumno")
class Alumno(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotBlank
    @field:Size(max = 60)
    var nombre: String = "",

    @field:NotBlank
    @field:Size(max = 80)
    var apellidos: String = "",

    @field:Email
    @field:Size(max = 120)
    var email: String? = null,

    @field:NotBlank
    @field:Size(max = 40)
    var curso: String = "",

    @field:Min(0)
    @field:Max(120)
    var edad: Int? = null,

    @field:DecimalMin("0.0")
    @field:DecimalMax("10.0")
    @Column(name = "nota_media")
    var notaMedia: Double? = null
)
