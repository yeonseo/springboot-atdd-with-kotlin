package com.example.atddsubway.line.domain

import com.example.atddsubway.core.domain.BaseEntity
import jakarta.persistence.*
import java.time.LocalTime

@Entity
class Line (
    @Column(unique = true, nullable = false)
    var name: String,

    var color: String,

    var startTime: LocalTime,

    var endTime: LocalTime,

    var intervalTime: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) : BaseEntity() {
    fun update(updateLine: Line) {
        this.name = updateLine.name
        this.color = updateLine.color
        this.startTime = updateLine.startTime
        this.endTime = updateLine.endTime
        this.intervalTime = updateLine.intervalTime
    }
}