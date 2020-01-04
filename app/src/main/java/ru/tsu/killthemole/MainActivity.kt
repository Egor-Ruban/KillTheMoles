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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.init(applicationContext)
        btn_play.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            val levelData = LevelData().apply {
                holes = Random.nextInt(4,16)
                difficulty = Random.nextInt(1,holes-2)
                speed = Random.nextLong(1000L,3000L)
                time = speed * Random.nextLong(3,30)
            }

            with(intent){
                putExtra("holes",levelData.holes)
                putExtra("difficulty", levelData.difficulty)
                putExtra("speed", levelData.speed)
                putExtra("time", levelData.time)
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

        btn_stats.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }
    }


}
