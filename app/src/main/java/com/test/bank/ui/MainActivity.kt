package com.test.bank.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.bank.ui.fragments.MainFragment
import com.test.bank.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(
            R.id.activity_main,
            MainFragment()
        ).commit()
    }
}