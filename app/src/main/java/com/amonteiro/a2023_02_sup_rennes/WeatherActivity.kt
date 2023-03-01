package com.amonteiro.a2023_02_sup_rennes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a2023_02_sup_rennes.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {
    //Instantiation
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {

            binding.progressBar.isVisible = true

            thread {
                val weather = RequestUtils.loadWeather("Toulouse")

                runOnUiThread {
                    binding.progressBar.isVisible = false
                    binding.tvData.text = "Il fait ${weather.main.temp}° à ${weather.name} avec un vent de ${weather.wind.speed} km/h"
                }
            }
            binding.tvData.setText("En cours...")
        }

    }
}