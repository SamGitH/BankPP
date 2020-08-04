package com.test.bank

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView

class MyCardView(context: Context, attrs: AttributeSet, val card: Card) : View (context, attrs) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

    }
}