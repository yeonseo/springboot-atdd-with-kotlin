package com.example.atddsubway.line.dto

data class LineStationResponse (
    val station: StationResponse,
    val preStationId: Long,
    val distance: Int,
    val duration: Int,
) {

}
