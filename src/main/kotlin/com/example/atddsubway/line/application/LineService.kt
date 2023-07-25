package com.example.atddsubway.line.application

import com.example.atddsubway.line.domain.Line
import com.example.atddsubway.line.domain.LineRepository
import com.example.atddsubway.line.dto.LineRequest
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class LineService @Autowired constructor(private val lineRepository: LineRepository) {
    fun saveLine(request: LineRequest): Line {
        return lineRepository.save(request.toLine())
    }

}