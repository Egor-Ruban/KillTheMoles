package ru.tsu.killthemole

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO создать статистику
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_play.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            val holes = Random.nextInt(1,21)
            val difficulty = Random.nextInt(1,holes)
            val speed = Random.nextLong(1000L,3000L)
            with(intent){
                putExtra("holes",holes)
                putExtra("difficulty", difficulty)
                putExtra("speed", speed)

            }
            startActivity(intent)
        }
        btn_levels.setOnClickListener {
            val intent = Intent(this, LevelsActivity::class.java)
            startActivity(intent)
        }
        btn_exit.setOnClickListener {
            finish()
        }
    }



}
