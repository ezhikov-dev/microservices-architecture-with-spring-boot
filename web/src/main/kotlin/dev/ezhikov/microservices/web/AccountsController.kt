package dev.ezhikov.microservices.web

import dev.ezhikov.microservices.backus.api.AccountTO
import dev.ezhikov.microservices.backus.api.CreateAccountTO
import dev.ezhikov.microservices.backus.core.AccountService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountsController(private var accountService: AccountService) {

    @GetMapping
    fun getAll(): List<AccountTO> = accountService.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): AccountTO? = accountService.getById(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): Unit = accountService.delete(id)

    @PostMapping
    fun create(@RequestBody to: CreateAccountTO): AccountTO = accountService.add(to)

}
