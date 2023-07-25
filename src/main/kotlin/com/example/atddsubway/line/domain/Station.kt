package com.example.atddsubway.line.domain

import com.example.atddsubway.core.domain.BaseEntity
import jakarta.persistence.*

@Entity
class Station(
    @Column(unique = true, nullable = false)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) : BaseEntity()

