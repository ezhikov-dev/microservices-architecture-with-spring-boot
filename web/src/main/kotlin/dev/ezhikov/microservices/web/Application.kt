package dev.ezhikov.microservices.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import feign.Feign
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter


@SpringBootApplication(
		scanBasePackages = [
			"dev.ezhikov.microservices.web",
			"dev.ezhikov.microservices.backus",
			"dev.ezhikov.microservices.dal.core"
		]
)
@EnableFeignClients
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
