package com.akpdev.dailyvita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.akpdev.dailyvita.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

