package com.example.atddsubway.line.application

import com.example.atddsubway.line.domain.Line
import com.example.atddsubway.line.domain.LineRepository
import com.example.atddsubway.line.dto.LineRequest
import com.example.atddsubway.line.dto.LineResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LineService @Autowired constructor(private val lineRepository: LineRepository) {
    fun saveLine(request: LineRequest): Line {
        return lineRepository.save(request.toLine())
    }

    @Transactional(readOnly = true)
    fun findLineById(id: Long): LineResponse {
        return lineRepository.findById(id)
            .map { LineResponse.of(it) }
            .orElseThrow{ RuntimeException() }
    }

    fun findAllLines(): List<LineResponse>? {
        return lineRepository.findAll().map { line: Line -> LineResponse.of(line) }
    }

}