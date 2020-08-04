package com.test.bank.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.test.bank.R
import com.test.bank.model.CardInfo
import kotlinx.android.synthetic.main.view_my_card.view.*

class MyCardView(context: Context, attrs: AttributeSet): LinearLayout (context, attrs) {

    var card: CardInfo? = null

    set (value) {
        field = value
        value?.run {
            vmc_number.text = number
            vmc_user_name.text = cardHolder
            vmc_valid_number.text = valid
            vmc_balance_static.text = balanceStatic
            vmc_balance_floating.text = balanceFloating
            vmc_card_img.setImageResource(imgId)
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_my_card, this, true)
    }
}