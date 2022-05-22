package com.example.testclimatesmart.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testclimatesmart.R
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Executors.newSingleThreadScheduledExecutor().schedule({
            NavigationToMain()
        }, 4, TimeUnit.SECONDS)

    }

    private fun NavigationToMain() {
        val intent = Intent(this, ClimateActivity::class.java)
        startActivity(intent)
    }


}