package com.example.testclimatesmart.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testclimatesmart.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClimateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_climate)
    }
}