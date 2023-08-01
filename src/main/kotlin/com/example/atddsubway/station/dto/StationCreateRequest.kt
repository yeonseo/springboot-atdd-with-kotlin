package com.example.atddsubway.station.dto

import com.example.atddsubway.station.domain.Station


data class StationCreateRequest(val name: String) {
    fun toStation(): Station = Station(name)
}
