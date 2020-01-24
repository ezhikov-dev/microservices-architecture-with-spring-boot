package dev.ezhikov.microservices.dal.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
        scanBasePackages = ["dev.ezhikov.microservices.dal"]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}