package com.example.atddsubway.line.domain

import com.example.atddsubway.core.domain.BaseEntity
import jakarta.persistence.*
import java.time.LocalTime

@Entity
class Line (
    @Column(unique = true, nullable = false)
    val name: String,

    val color: String,

    val startTime: LocalTime,

    val endTime: LocalTime,

    val intervalTime: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) : BaseEntity()