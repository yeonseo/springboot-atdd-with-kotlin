package com.example.atddsubway.line.dto

import com.example.atddsubway.station.dto.StationResponse

data class LineStationResponse (
    val station: StationResponse,
    val preStationId: Long,
    val distance: Int,
    val duration: Int,
) {

}
