package com.amonteiro.a2023_02_sup_rennes

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso

class WeatherViewModel : ViewModel() {
    var errorMessage = ""
    var data : WeatherBean? = null

    fun loadData(city: String){
        errorMessage = ""
        data = null
        try {
            //Requêtes
            data = RequestUtils.loadWeather(city)
        }
        catch (e: Exception) {
            //J'affiche le detail de l'erreur dans la console
            e.printStackTrace()
            errorMessage = e.message ?: "Une erreur est survenue"
        }
    }
}