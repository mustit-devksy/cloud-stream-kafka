package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import java.util.function.Consumer

data class OrderMessage(
  val orderNo: Long
)

@Configuration
class OrderCompleteConsumer {
  @Bean
  fun orderComplete(): Consumer<Message<OrderMessage>> {
    return Consumer<Message<OrderMessage>> { message -> println("OrderCompleteConsumer > orderComplete > ${message.payload}") }
  }

  @Bean
  fun orderPush(): Consumer<Message<OrderMessage>> {
    return Consumer<Message<OrderMessage>> { message -> println("OrderCompleteConsumer > orderPush > ${message.payload}") }
  }
}

