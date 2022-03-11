package com.example.demo

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component


@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
  runApplication<DemoApplication>(*args)
}

@Component
class applicationRunner(private val orderProducer: OrderProducer): ApplicationRunner {
  var index = 0L
  override fun run(args: ApplicationArguments?) {
    while(true) {
      Thread.sleep(1000)
      orderProducer.order(OrderMessage(index++))
    }
  }
}