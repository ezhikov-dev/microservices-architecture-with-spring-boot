package dev.ezhikov.microservices.web

import feign.Feign
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Configuration
@ConditionalOnClass(Feign::class)
class FeignMappingDefaultConfiguration {
    @Bean
    fun feignWebRegistrations(): WebMvcRegistrations {
        return WebMvcRegistrationsAdapterTest()
    }

    private class FeignFilterRequestMappingHandlerMapping : RequestMappingHandlerMapping() {
        override fun isHandler(beanType: Class<*>): Boolean {
            return super.isHandler(beanType) && AnnotationUtils.findAnnotation(beanType, FeignClient::class.java) == null
        }
    }

    private class WebMvcRegistrationsAdapterTest : WebMvcRegistrations {
        override fun getRequestMappingHandlerMapping(): RequestMappingHandlerMapping {
            return FeignFilterRequestMappingHandlerMapping()
        }
    }
}