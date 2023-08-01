package com.example.atddsubway.station.dto

import com.example.atddsubway.station.domain.Station
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true) // 알 수 없는 속성을 무시하고 예외를 방지
class StationResponse (
    val id: Long,
    val name: String,
    val createdDate: LocalDateTime?,
    val modifiedDate: LocalDateTime?
) {
    companion object {
        fun of(station: Station): StationResponse {
            return StationResponse(
                station.id,
                station.name,
                station.createdDate,
                station.updatedDate
            )
        }
    }

}
