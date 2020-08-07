package com.test.bank.api.model

data class UserApiModel(
    val card_number: String,
    val type: String,
    val cardholder_name: String,
    val valid: String,
    val balance: Float,
    val transaction_history: List<HistoryApiModel>
)