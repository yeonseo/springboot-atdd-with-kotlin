package com.example.atddsubway.line.domain;

import org.springframework.data.jpa.repository.JpaRepository

interface LineRepository : JpaRepository<Line, Long> {
}