package com.example.demo

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.integration.support.MessageBuilder
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.stereotype.Component
import java.util.*

data class OrderMessage(
  val orderNo: Long
)

const val BINDING_NAME_OF_ORDER = "order-processing"

@Component
class OrderProducer(private val streamBridge: StreamBridge) {
  fun order(orderMessage: OrderMessage) {
    println("OrderProducer >>> $orderMessage")
    streamBridge.send(
      BINDING_NAME_OF_ORDER, MessageBuilder
        .withPayload(orderMessage)
        .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
        .build()
    )
  }
}