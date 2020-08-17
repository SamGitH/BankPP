package com.test.bank.model

data class Card (
    val number: String,
    val type: CardType,
    val cardHolder: String,
    val valid: String,
    val balance: Currency,
    var history: List<History>
)