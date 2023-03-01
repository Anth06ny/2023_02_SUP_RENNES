package com.amonteiro.a2023_02_sup_rennes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a2023_02_sup_rennes.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {
    //Instantiation
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}