package dev.ezhikov.microservices.backus.api

data class AccountTO(
        val id: Int,
        val name: String,
        val description: String?,
        val type: AccountType,
        val details: DetailType,
        val parentId: Int?,
        var deleted: Boolean,
        val initialBalance: Int
)

data class CreateAccountTO(
        val name: String,
        val type: AccountType,
        val details: DetailType,
        val description: String? = null,
        val deleted: Boolean = false,
        val parentId: Int? = null,
        val initialBalance: Int? = null
)