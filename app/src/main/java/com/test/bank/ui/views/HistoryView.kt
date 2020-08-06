package com.test.bank.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.test.bank.R

class HistoryView (context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_history, this, true)
    }
}