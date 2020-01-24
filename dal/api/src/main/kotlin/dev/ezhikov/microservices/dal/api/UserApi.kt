package dev.ezhikov.microservices.dal.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping("/api/user")
interface UserApi {
    @GetMapping
    fun getAll(): Collection<User>

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): User?

    @PostMapping
    fun add(@RequestParam("name") name: String)
}