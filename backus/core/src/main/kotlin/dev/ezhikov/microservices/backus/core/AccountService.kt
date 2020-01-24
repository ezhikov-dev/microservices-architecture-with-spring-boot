package dev.ezhikov.microservices.backus.core

import dev.ezhikov.microservices.backus.api.AccountTO
import dev.ezhikov.microservices.backus.api.CreateAccountTO
import org.springframework.stereotype.Service

@Service
class AccountService {

    private val accounts = mutableListOf<AccountTO>()

    fun add(to: CreateAccountTO): AccountTO {
        val parentId = to.parentId?.let { checkNotNull(getById(it)).id }
        assert(accounts.none { it.name.equals(to.name, ignoreCase = true) }) { "Account with name: ${to.name} is already exists." }
        val newTransaction = AccountTO(
                accounts.size,
                to.name,
                to.description,
                to.type,
                to.details,
                parentId,
                to.deleted,
                to.initialBalance ?: 0)
        accounts.add(newTransaction)
        return newTransaction
    }

    fun delete(id: Int) {
        getById(id)?.deleted = true
    }

    fun getById(id: Int): AccountTO? = accounts.getOrNull(id)

    fun getAll() = accounts.filter { it.deleted.not() }
}