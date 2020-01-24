package dev.ezhikov.microservices.web

import dev.ezhikov.microservices.dal.api.UserApi
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(value = "userApiClient", url = "localhost:8081")
interface UserApiClient : UserApi