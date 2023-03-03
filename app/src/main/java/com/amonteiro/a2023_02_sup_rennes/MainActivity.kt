package com.amonteiro.a2023_02_sup_rennes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a2023_02_sup_rennes.databinding.ActivityMainBinding

const val MENU_METEO = 1
const val MENU_POKEMON = 2
const val MENU_RECYCLER_VIEW = 3

class MainActivity : AppCompatActivity() {

    //Instantiation
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Affichage
        setContentView(binding.root)

        //clic sur le bouton valider
        binding.btValidate.setOnClickListener {
            if (binding.rbLike.isChecked) {
                binding.et.setText(binding.rbLike.text)
            }
            else if (binding.rbDislike.isChecked) {
                binding.et.setText(binding.rbDislike.text)
            }
            binding.iv.setImageResource(R.drawable.baseline_flag_24)
            binding.iv.setColorFilter(getColor(R.color.purple_200))
        }

        binding.btCancel.setOnClickListener {
            binding.rg.clearCheck()
            binding.et.setText("")
            binding.iv.setImageResource(R.drawable.baseline_delete_forever_24)
        }
    }


    //Callback création du menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_METEO, 0, "Météo")
        menu.add(0, MENU_POKEMON, 0, "Pokemon")
        menu.add(0, MENU_RECYCLER_VIEW, 0, "RecyclerView")
        return super.onCreateOptionsMenu(menu)
    }

    //Callback clic sur les menus
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == MENU_METEO) {
            //Changement d'écran
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }
        else if (item.itemId == MENU_POKEMON) {
            //Changement d'écran
            val intent = Intent(this, PokemonActivity::class.java)
            startActivity(intent)
        }
        else if (item.itemId == MENU_RECYCLER_VIEW) {
            //Changement d'écran
            val intent = Intent(this, WeatherAroundActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }


}