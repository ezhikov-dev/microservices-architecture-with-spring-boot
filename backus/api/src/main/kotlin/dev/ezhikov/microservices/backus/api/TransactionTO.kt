package dev.ezhikov.microservices.backus.api

import java.util.Date

data class TransactionTO(
        val id: Int,
        val date: Date,
        val amount: Int,
        var deleted: Boolean = false
)

data class CreateTransactionTO(
        val date: Date,
        val amount: Int
)