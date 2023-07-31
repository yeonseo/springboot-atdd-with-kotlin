package com.example.atddsubway.line.controller

import com.example.atddsubway.line.application.LineService
import com.example.atddsubway.line.domain.Line
import com.example.atddsubway.line.dto.LineRequest
import com.example.atddsubway.line.dto.LineResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/lines")
class LineController @Autowired constructor(
    val lineService: LineService
) {
    @PostMapping
    fun createLine(@RequestBody lineRequest: LineRequest) : ResponseEntity<LineResponse> {
        val line: Line = lineService.saveLine(lineRequest)
        return ResponseEntity.created(URI.create("/lines/" + line.id)).body(LineResponse.of(line))
    }

    @GetMapping("/{id}")
    fun findLineById(@PathVariable id: Long): ResponseEntity<LineResponse> {
        return ResponseEntity.ok(lineService.findLineById(id))
    }

    @GetMapping
    fun findAllLines(): ResponseEntity<List<LineResponse>> {
        return ResponseEntity.ok(lineService.findAllLines())
    }

    @PutMapping("/{id}")
    fun updateLine(@PathVariable id: Long, @RequestBody lineUpdateRequest: LineRequest): ResponseEntity<Void> {
        lineService.updateLine(id, lineUpdateRequest)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteLine(@PathVariable id: Long): ResponseEntity<*> {
        lineService.deleteLineById(id)
        return ResponseEntity.noContent().build<Any>()
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleIllegalArgsException(e: DataIntegrityViolationException?): ResponseEntity<*> {
        return ResponseEntity.badRequest().build<Any>()
    }
}
