package ru.tsu.killthemole

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), View.OnClickListener {private val places = mutableListOf<Button>()
    private val holes = mutableListOf<Button>()
    private var moles = mutableListOf<Button>()
    private var score = 0L
    private var scoreToBeat = 0L
    private var maxScore = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO добавить паузу
        //TODO добавить полицая
        setContentView(R.layout.activity_game)
        initViews()
        places.shuffle()
        var holesNum = intent.getIntExtra("holes", 8)
        for(i in 0 until holesNum) {
            places[i].background = (ContextCompat.getDrawable(this, R.drawable.weed_again))
            holes.add(places[i])
            places[i].setOnClickListener(this)
        }
        tv_score.text = 0.toString()
        var gameTime = intent.getLongExtra("time",30000L)
        var spawnRate = intent.getLongExtra("speed", 3000L)
        var numOfSpawning = intent.getIntExtra("difficulty", 2)
        maxScore = (gameTime/spawnRate)*numOfSpawning
        scoreToBeat = (maxScore*9/10)
        startGame(gameTime, spawnRate, numOfSpawning)
    }

    private fun startGame(gameTime: Long, spawnRate : Long, numOfSpawning : Int){
        val timer = object: CountDownTimer(gameTime, 1000){
            override fun onTick(p0: Long) {
                tv_estimated_time.text = "${p0/1000} сек."
            }

            override fun onFinish() {

            }
        }.start()
        val updater = object: CountDownTimer(gameTime, spawnRate){
            override fun onTick(p0: Long) {
                updateField(numOfSpawning)

            }

            override fun onFinish() {
                for(mole in moles){
                    holes.add(mole)
                    mole.background = ContextCompat.getDrawable(baseContext, R.drawable.weed_again)
                }
                moles = mutableListOf()
                val message = if(score>=scoreToBeat){
                    """уровень пройден
                        очков собрано $score
                        очков пропущено ${maxScore - score}""".trimIndent()
                } else {
                    """уровень не пройден 
                    очков собрано $score
                    очков пропущено ${maxScore - score}""".trimIndent()
                }
                Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
                val resultData = Intent()
                resultData.putExtra("isPassed",score>=scoreToBeat)
                setResult(Activity.RESULT_OK,resultData)
                Repository.add(Repository.ATTEMPTS,1)
                Repository.add(Repository.COLLECTED,score.toInt())
                if(score>=scoreToBeat) {
                    Repository.add(Repository.SUCCESS,1)
                }
                finish()
            }
        }.start()
    }

    private fun updateField(numOfSpawning: Int){
        for(mole in moles){
            holes.add(mole)
            mole.background = ContextCompat.getDrawable(baseContext, R.drawable.weed_again)
        }
        moles = mutableListOf()
        for(i in 0 until numOfSpawning) {
            holes.shuffle()
            holes[0].background = ContextCompat.getDrawable(baseContext, R.drawable.kos)
            moles.add(holes[0])
            holes.removeAt(0)
        }
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