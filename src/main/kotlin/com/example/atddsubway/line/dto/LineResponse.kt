package com.example.atddsubway.line.dto

import com.example.atddsubway.line.domain.Line
import java.time.LocalDateTime

data class LineResponse (
    val id: Long,
    val name: String,
    val color: String,
    val startTime: String,
    val endTime: String,
    val intervalTime: Int,
    val stations: List<LineStationResponse>,
    val createdDate: LocalDateTime?,
    val modifiedDate: LocalDateTime?
) {
    companion object {
        fun of(line: Line, stations: List<LineStationResponse> = listOf()): LineResponse {
            return LineResponse(
                line.id,
                line.name,
                line.color,
                line.startTime.toString(),
                line.endTime.toString(),
                line.intervalTime,
                stations,
                line.createdDate,
                line.updatedDate
            )
        }
    }

}
