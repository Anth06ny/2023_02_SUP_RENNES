package com.amonteiro.a2023_02_sup_rennes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a2023_02_sup_rennes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //Instantiation
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Affichage
        setContentView(binding.root)

        binding.btValidate.setOnClickListener {
            println("clic")
            binding.et.setText("Clic sur Valider")
        }

        binding.btCancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       if(v === binding.btCancel) {
           binding.et.setText("Clic sur Annuler")
       }
    }
}