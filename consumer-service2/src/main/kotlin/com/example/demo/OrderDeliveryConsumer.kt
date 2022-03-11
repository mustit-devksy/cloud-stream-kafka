package com.example.demo

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.support.MessageBuilder
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import java.util.*
import java.util.function.Consumer

const val BINDING_NAME_OF_ORDER_COMPLETE = "orderDelivery-out-0"
const val BINDING_NAME_OF_ORDER_PUSH = "order-push"

data class OrderMessage(
  val orderNo: Long
)

data class OrderComplete(
  val orderNo: Long
)

@Configuration
class OrderDeliveryConsumer(private val streamBridge: StreamBridge) {
  @Bean
  fun orderDelivery(): Consumer<Message<OrderMessage>> {
    return Consumer<Message<OrderMessage>> { message ->
      println("Kafka OrderCompleteConsumer > ${message.payload}")
      streamBridge.send(
        BINDING_NAME_OF_ORDER_COMPLETE, MessageBuilder
          .withPayload(OrderMessage(message.payload.orderNo))
          .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
          .build()
      )

      streamBridge.send(
        BINDING_NAME_OF_ORDER_PUSH, MessageBuilder
          .withPayload(OrderComplete(message.payload.orderNo))
          .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
          .build()
      )
    }
  }
}

