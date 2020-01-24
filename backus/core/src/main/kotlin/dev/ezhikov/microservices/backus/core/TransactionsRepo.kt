package dev.ezhikov.microservices.backus.core

import dev.ezhikov.microservices.backus.api.CreateAccountTO
import dev.ezhikov.microservices.backus.api.AccountTO
import dev.ezhikov.microservices.backus.api.CreateTransactionTO
import dev.ezhikov.microservices.backus.api.TransactionTO
import org.springframework.stereotype.Repository

@Repository
class TransactionsRepo {

    private val transactions = mutableListOf<TransactionTO>()

    fun add(to: CreateTransactionTO): TransactionTO {
        val newTransaction = TransactionTO(transactions.size, to.date, to.amount)
        transactions.add(newTransaction)
        return newTransaction
    }

    fun delete(id: Int) {
        getById(id)?.deleted = true
    }

    fun getById(id: Int): TransactionTO? = transactions.getOrNull(id)

    fun getAll() = transactions.filter { it.deleted.not() }
}