package com.example.atddsubway.util.kafka

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.*


object ConsumerAsync {
    @JvmStatic
    fun main(args: Array<String>) {
        val props = Properties() //Properties 오브젝트를 시작합니다.
        props["bootstrap.servers"] =
            "peter-kafka01.foo.bar:9092,peter-kafka02.foo.bar:9092,peter-kafka03.foo.bar:9092" //브로커 리스트를 정의합니다.
        props["group.id"] = "peter-consumer01" //컨슈머 그룹 아이디 정의합니다.
        props["enable.auto.commit"] = "false" //자동 커밋을 사용하지 않습니다.
        props["auto.offset.reset"] = "latest" //컨슈머 오프셋을 찾지 못하는 경우 latest로 초기화 합니다. 가장 최근부터 메시지를 가져오게 됩니다.
        props["key.deserializer"] =
            "org.apache.kafka.common.serialization.StringDeserializer" //문자열을 사용했으므로 StringDeserializer 지정합니다.
        props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"

        val consumer = KafkaConsumer<String, String>(props) //Properties 오브젝트를 전달하여 새 컨슈머를 생성합니다.
        consumer.subscribe(mutableListOf("peter-basic01")) //구독할 토픽을 지정합니다.

        try {
            while (true) { // 메시지를 가져오기 위해 카프카에 지속적으로 poll()을 하게 됩니다.
                val records = consumer.poll(1000) //컨슈머는 폴링하는 것을 계속 유지하며, 타임 아웃 주기를 설정합니다.해당 시간만큼 블럭합니다.
                for (record in records) { //poll()은 레코드 전체를 리턴하고, 하나의 메시지만 가져오는 것이 아니므로, 반복문 처리합니다.
                    System.out.printf(
                        "Topic: %s, Partition: %s, Offset: %d, Key: %s, Value: %s\n",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value()
                    )
                }
                consumer.commitAsync() //현재 배치를 통해 읽은 모든 메시지들을 처리한 후, 추가 메시지를 폴링하기 전 현재의 오프셋을 비동기 커밋합니다.
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            consumer.close() //컨슈머를 종료합니다.
        }
    }
}