package dev.ezhikov.microservices.backus.core

import dev.ezhikov.microservices.backus.api.AccountType
import dev.ezhikov.microservices.backus.api.CreateAccountTO
import dev.ezhikov.microservices.backus.api.DetailType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AccountServiceTest {

    private lateinit var service: AccountService

    @BeforeEach
    fun setup() {
        service = AccountService()
    }

    @Test
    fun `Can create new account`() {
        val to = CreateAccountTO("Test", AccountType.BANK, DetailType.CHECKING)
        val newAccount = service.add(to)
        assertEquals(0, newAccount.id)
        assertEquals(to.name, newAccount.name)
        assertEquals(to.description, newAccount.description)
        assertEquals(to.type, newAccount.type)
        assertEquals(to.details, newAccount.details)
        assertEquals(0, newAccount.initialBalance)
        assertNull(newAccount.parentId)
        assertFalse(newAccount.deleted)
    }

    @Test
    fun `Can create sub account`() {
        val parent = service.add(CreateAccountTO("Parent", AccountType.BANK, DetailType.CHECKING))
        val subAccount = service.add(CreateAccountTO("Sub-Account", AccountType.BANK, DetailType.SAVING, parentId = parent.id))
        assertEquals("Sub-Account", subAccount.name)
        assertEquals(parent.id, subAccount.parentId)
    }

    @Test
    fun `Can not create sub account, if parent does not exist`() {
        assertThrows(IllegalStateException::class.java) {
            service.add(CreateAccountTO("Sub-Account", AccountType.BANK, DetailType.SAVING, parentId = Int.MAX_VALUE))
        }
    }

    @Test
    fun `Can not create account, if name is already exist`() {
        service.add(CreateAccountTO("Account", AccountType.BANK, DetailType.SAVING))
        assertThrows(AssertionError::class.java) {
            service.add(CreateAccountTO("Account", AccountType.CREDIT_CARD, DetailType.CREDIT_CARD))
        }
    }

    @Test
    fun `Can get account by id`() {
        val expected = service.add(CreateAccountTO("Account", AccountType.BANK, DetailType.SAVING))
        val actual = service.getById(expected.id)
        assertEquals(expected, actual)
    }

    @Test
    fun `Can delete account`() {
        val account = service.add(CreateAccountTO("Account", AccountType.BANK, DetailType.SAVING))
        service.delete(account.id)
        assertTrue(service.getById(account.id)?.deleted ?: false)
    }

    @Test
    fun `Can get all non deleted accounts`() {
        service.add(CreateAccountTO("Account 1", AccountType.BANK, DetailType.SAVING))
        service.add(CreateAccountTO("Account 2", AccountType.BANK, DetailType.SAVING))
        val account3 = service.add(CreateAccountTO("Account 3", AccountType.BANK, DetailType.SAVING))

        assertEquals(service.getAll().size, 3)
        service.delete(account3.id)

        assertEquals(service.getAll().size, 2)
    }

}