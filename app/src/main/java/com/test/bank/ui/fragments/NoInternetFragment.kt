package com.test.bank.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.bank.R
import kotlinx.android.synthetic.main.fragment_no_internet.*

class NoInternetFragment: Fragment(R.layout.fragment_no_internet) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fni_btn_refresh.animate().rotation(1000.toFloat()).setDuration(1000).start()
        fni_btn_refresh.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}