package dev.ezhikov.microservices.dal.core

import dev.ezhikov.microservices.dal.api.User
import org.springframework.stereotype.Repository

@Repository
class UserRepo {

    private var userId: Int = 0
    private val users: MutableMap<Int, User> = mutableMapOf()

    fun add(name: String) {
        val id = ++userId
        users[id] = User(id, name)
    }

    fun getById(id: Int): User? = users[id]

    fun getAll() = users.values
}