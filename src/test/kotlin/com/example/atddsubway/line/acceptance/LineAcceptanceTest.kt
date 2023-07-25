package com.example.atddsubway.line.acceptance

import com.example.atddsubway.AcceptanceTest
import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@DisplayName("지하철 노선 관련 기능")
class LineAcceptanceTest  : AcceptanceTest() {
    @DisplayName("지하철 노선을 생성한다.")
    @Test
    fun createLine() {
        // given
        // when
        // 지하철_노선_생성_요청
        val params: MutableMap<String, String> = HashMap()
        params["name"] = "2호선"
        params["color"] = "bg-green-600"
        params["startTime"] = "05:00"
        params["endTime"] = "23:30"
        params["intervalTime"] = "10"

        val response = RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .`when`()
            .post("/lines")
            .then().log().all().extract()


        // then
        // 지하철_노선_생성됨
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        assertThat(response.header("Location")).isNotBlank()
    }
}