package com.example.atddsubway.util.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord

import java.util.Properties;

object ProducerAsync {
    @JvmStatic
    fun main(args: Array<String>) {
        val props = Properties() //Properties 오브젝트를 시작합니다.
        props["bootstrap.servers"] =
            "peter-kafka01.foo.bar:9092,peter-kafka02.foo.bar:9092,peter-kafka03.foo.bar:9092" //브로커 리스트를 정의합니다.
        props["key.serializer"] =
            "org.apache.kafka.common.serialization.StringSerializer" //메시지 키와 벨류에 문자열을 지정하므로 내장된 StringSerializer를 지정합니다.
        props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        val producer = KafkaProducer<String, String>(props) //Properties 오브젝트를 전달해 새 프로듀서를 생성합니다.
        try {
            for (i in 0..2) {
                val record = ProducerRecord<String, String>(
                    "peter-basic01",
                    "Apache Kafka is a distributed streaming platform - $i"
                ) //ProducerRecord 오브젝트를 생성합니다.
                producer.send(record, PeterProducerCallback(record)) //프로듀서에서 레코드를 보낼 때 콜백 오브젝트를 같이 보냅니다.
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            producer.close() // 프로듀서 종료
        }
    }
}