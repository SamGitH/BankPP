package com.test.bank.model

enum class CardType (var type: String) {
    MASTER_CARD ("mastercard"),
    VISA ("visa"),
    UNION_PAY ("unionpay"),
    DEFAULT ("default");

    companion object {
        fun getCardType (type: String): CardType{

            for (cardType in CardType.values()){
                if(cardType.type == type)
                    return cardType
            }

            return DEFAULT
        }
    }
}