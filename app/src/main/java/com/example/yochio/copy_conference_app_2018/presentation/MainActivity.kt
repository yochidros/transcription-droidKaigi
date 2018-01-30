package com.example.yochio.copy_conference_app_2018.presentation

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.yochio.copy_conference_app_2018.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
