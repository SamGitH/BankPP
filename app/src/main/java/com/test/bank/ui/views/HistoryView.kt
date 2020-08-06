package com.test.bank.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.test.bank.R
import com.test.bank.model.HistoryInfo
import com.test.bank.ui.adapters.HistoryAdapter
import kotlinx.android.synthetic.main.view_history.view.*

class HistoryView (context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter(history!!)
    }

    var history: ArrayList<HistoryInfo>? = null
    set(value) {
        field = value
        value?.run {
            vh_rv.adapter = adapter
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_history, this, true)
    }
}