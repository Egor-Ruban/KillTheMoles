package ru.tsu.killthemole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val places = mutableListOf<TableRow>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        places.shuffle()
        for(i in 0..8){
            places[i].setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        }


}

    private fun initViews(){
        places.add(place11)
        places.add(place12)
        places.add(place13)
        places.add(place21)
        places.add(place22)
        places.add(place23)
        places.add(place31)
        places.add(place32)
        places.add(place33)
        places.add(place41)
        places.add(place42)
        places.add(place43)
        places.add(place51)
        places.add(place52)
        places.add(place53)
        places.add(place61)
        places.add(place62)
        places.add(place63)
        places.add(place71)
        places.add(place72)
        places.add(place73)
        places.add(place81)
        places.add(place82)
        places.add(place83)
    }
}
