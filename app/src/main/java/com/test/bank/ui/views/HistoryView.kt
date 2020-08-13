package com.test.bank.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.bank.R
import com.test.bank.model.HistoryInfo
import com.test.bank.ui.adapters.HistoryAdapter
import kotlinx.android.synthetic.main.view_history.view.*

class HistoryView (context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    val adapter: HistoryAdapter by lazy {
        HistoryAdapter(history)
    }

    var history: ArrayList<HistoryInfo> = arrayListOf()
//    set(value) {
//        field = value
//        value.apply {
//            this@HistoryView.vh_rv.adapter = adapter
//            this@HistoryView.vh_rv.layoutManager = LinearLayoutManager(context)
//        }
//    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_history, this, true)
        this.vh_rv.adapter = adapter
        this.vh_rv.layoutManager = LinearLayoutManager(context)
    }
}