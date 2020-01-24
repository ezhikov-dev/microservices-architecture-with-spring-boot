package dev.ezhikov.microservices.web

import dev.ezhikov.microservices.backus.api.CreateTransactionTO
import dev.ezhikov.microservices.backus.core.TransactionsRepo
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/transaction")
class TransactionsController(private var transactionsRepo: TransactionsRepo) {

    @GetMapping
    fun getAll() = transactionsRepo.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int) = transactionsRepo.getById(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int) = transactionsRepo.delete(id)

    @PostMapping
    fun create(@RequestBody to: CreateTransactionTO) = transactionsRepo.add(to)

}
