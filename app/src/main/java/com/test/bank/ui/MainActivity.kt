package com.test.bank.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.test.bank.ui.fragments.MainFragment
import com.test.bank.R
import com.test.bank.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Navigation
//            .findNavController(this, R.id.nav_host_fragment)
//            .navigate(R.id.mainFragment)

//        findNavController(R.id.nav_host_fragment).navigate(R.id.mainFragment)
    }
}