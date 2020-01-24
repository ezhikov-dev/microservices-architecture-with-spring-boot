package dev.ezhikov.microservices.dal.web

import dev.ezhikov.microservices.dal.api.User
import dev.ezhikov.microservices.dal.api.UserApi
import dev.ezhikov.microservices.dal.core.UserRepo
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
        private val userRepo: UserRepo
) : UserApi {
    override fun getAll(): Collection<User> = userRepo.getAll()

    override fun get(id: Int): User? = userRepo.getById(id)

    override fun add(name: String) = userRepo.add(name)
}