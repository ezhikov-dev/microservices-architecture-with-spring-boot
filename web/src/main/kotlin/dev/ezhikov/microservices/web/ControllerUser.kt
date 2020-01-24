package dev.ezhikov.microservices.web

import dev.ezhikov.microservices.dal.api.UserApi
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class ControllerUser(private var userRepo: UserApi) {

    @GetMapping
    fun getAll() = userRepo.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int) = userRepo.get(id)

    @PostMapping
    fun create(@RequestParam("name") name: String) = userRepo.add(name)

    @GetMapping("/error")
    fun error(): Nothing {
        throw NullPointerException()
    }

}
