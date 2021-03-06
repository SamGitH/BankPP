package com.test.bank.model

import androidx.annotation.DrawableRes

data class CardInfo (
    val number: String,
    val cardHolder: String,
    val valid: String,
    val balanceStatic: String,
    var balanceFloating: String,
    @DrawableRes val imgId: Int
)