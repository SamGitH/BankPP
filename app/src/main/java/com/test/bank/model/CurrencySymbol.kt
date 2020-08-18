package com.test.bank.model

enum class CurrencySymbol(var symbol: String) {
    DOLLAR ("$"),
    POUND ("£"),
    EURO ("€"),
    RUBLE ("₽"),
    DEFAULT ("");

    companion object {
        fun getSymbol (symbol: String): CurrencySymbol{

            for (currencySymbol in values()){
                if(currencySymbol.symbol == symbol)
                    return currencySymbol
            }

            return DEFAULT
        }
    }
}