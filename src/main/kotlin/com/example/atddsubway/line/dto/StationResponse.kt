package com.example.atddsubway.line.dto

import com.example.atddsubway.line.domain.Station

class StationResponse (
    val id: Long,
    val name: String,
    val createdDate: String?,
    val modifiedDate: String?
) {
    companion object {
        fun of(station: Station): StationResponse {
            return StationResponse(
                station.id,
                station.name,
                station.createdDate.toString(),
                station.updatedDate.toString()
            )
        }
    }

}
