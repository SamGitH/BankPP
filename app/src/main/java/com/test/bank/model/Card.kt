package com.test.bank.model

data class Card(
    val number: String,
    val type: String,
    val cardHolder: String,
    val valid: String,
    val balance: Currency
)