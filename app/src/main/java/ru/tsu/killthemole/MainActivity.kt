package ru.tsu.killthemole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val places = mutableListOf<Button>()
    private val holes = mutableListOf<Button>()
    private val moles = mutableListOf<Button>()
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO добавить иконку
        //TODO создать окно меню
        //TODO добавить выбор сложности (время/кол-во дыр)
        //TODO проверку, что numOfSpawning не больше кол-ва дыр
        //TODO добавить паузу
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        places.shuffle()
        for(i in 0..5) {
            places[i].background = (ContextCompat.getDrawable(this, R.drawable.weed_again))
            holes.add(places[i])
            places[i].setOnClickListener(this)
        }
        tv_score.text = 0.toString()
        var gameTime = 30000L
        var spawnRate = 1000L
        var numOfSpawning = 2
        onStartGame(gameTime, spawnRate, numOfSpawning)
    }

    private fun onStartGame(gameTime: Long, spawnRate : Long, numOfSpawning : Int){
        val timer = object: CountDownTimer(gameTime, spawnRate){
            override fun onTick(p0: Long) {
                updateField(numOfSpawning)
            }

            override fun onFinish() {
                Toast.makeText(baseContext,"You`ve got $score points",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun updateField(numOfSpawning: Int){
        for(mole in moles){
            moles.remove(mole)
            holes.add(mole)
            mole.background = ContextCompat.getDrawable(baseContext, R.drawable.weed_again)
        }
        holes.shuffle()
        for(i in 0 until numOfSpawning)
            holes[0].background = ContextCompat.getDrawable(baseContext, R.drawable.kos)
        moles.add(holes[0])
        holes.removeAt(0)
    }

    override fun onClick(p0: View?) {
        if(moles.contains(p0)){
            score++
            tv_score.text = score.toString()
            moles.remove(p0)
            holes.add(p0 as Button)
            p0.background = ContextCompat.getDrawable(this, R.drawable.weed_again)
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
    }
}
