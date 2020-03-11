package ru.tsu.killthemole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.init(applicationContext)
        btnPlay.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            val levelData = LevelSettings().apply {
                holes = Random.nextInt(4,16)
                difficulty = Random.nextInt(1,holes - 2)
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

        btnLevels.setOnClickListener {
            val intent = Intent(this, LevelsActivity::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finish()
        }

        btnStats.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }
    }


}
