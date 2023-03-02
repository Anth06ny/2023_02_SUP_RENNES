package com.amonteiro.a2023_02_sup_rennes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a2023_02_sup_rennes.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {
    //Instantiation
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {
            //Préparation de la requete graphiquement
            binding.progressBar.isVisible = true

            val city = binding.etCityName.text.toString()
            thread {
                model.loadData(city)

                //Mise à jour graphique obligatoirement sur le thread principale
                runOnUiThread {
                    refreshScreen()
                    binding.progressBar.isVisible = false
                }
            }
        }
        //Arriver sur l'écran je mets à jour ems données au cas ou elles sont présentes
        refreshScreen()

    }

    fun refreshScreen() {
        //Affichage des données
        binding.tv.text = model.data?.name ?: "-"
        binding.tvWind.text = model.data?.wind?.speed?.toString() ?: "-"
        binding.tvTemp.text = "${model.data?.main?.temp ?: "-"}°"
        binding.tvMinMax.text = "(${model.data?.main?.temp_min ?: "-"}° / ${model.data?.main?.temp_max ?: "-"}°)"

        if (!model.data?.weather.isNullOrEmpty()) {
            binding.tvDesc.text = model.data?.weather?.get(0)?.description ?: "-"
            Picasso.get().load("https://openweathermap.org/img/wn/${model.data?.weather?.get(0)?.icon}@4x.png")
                .placeholder(R.drawable.baseline_whatshot_24)
                .error(R.drawable.baseline_delete_forever_24)
                .into(binding.ivTemp)
        }

        //Affichage de l'erreur
        if (model.errorMessage.isNotBlank()) {
            binding.tvError.text = model.errorMessage
            binding.tvError.isVisible = true
        }
        else {
            binding.tvError.isVisible = false
        }
    }
}