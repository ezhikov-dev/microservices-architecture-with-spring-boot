package dev.ezhikov.microservices.backus.api

enum class AccountType(val displayName: String) {
    BANK("Bank"),
    CREDIT_CARD("Credit Card")
}

enum class DetailType(val displayName:String){
    CHECKING("Checking"),
    SAVING("Saving"),
    CREDIT_CARD("Credit Card")
}