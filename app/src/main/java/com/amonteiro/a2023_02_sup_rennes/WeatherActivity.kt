package com.amonteiro.a2023_02_sup_rennes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a2023_02_sup_rennes.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {
    //Instantiation
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {

            //Préparation de la requete graphiquement
            binding.progressBar.isVisible = true
            binding.tvError.isVisible = false

            val city = binding.etCityName.text.toString()


            thread {
                try {
                    //Requêtes
                    val weather = RequestUtils.loadWeather(city)

                    //Mise à jour graphique obligatoirement sur le thread principale
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        binding.tv.text = weather.name
                        binding.tvWind.text = weather.wind.speed.toString()
                        binding.tvTemp.text = "${weather.main.temp}°"
                        binding.tvMinMax.text = "(${weather.main.temp_min}° / ${weather.main.temp_max}°)"

                        if (weather.weather.isNotEmpty()) {
                            binding.tvDesc.text = weather.weather[0].description
                            Picasso.get().load("https://openweathermap.org/img/wn/${weather.weather[0].icon}@4x.png")
                                .placeholder(R.drawable.baseline_whatshot_24)
                                .error(R.drawable.baseline_delete_forever_24)
                                .into(binding.ivTemp)
                        }
                    }
                }
                catch (e: Exception) {
                    //J'affiche le detail de l'erreur dans la console
                    e.printStackTrace()
                    runOnUiThread {
                        binding.tvError.text = e.message
                        binding.tvError.isVisible = true
                        binding.progressBar.isVisible = false
                    }
                }
            }

        }

    }
}