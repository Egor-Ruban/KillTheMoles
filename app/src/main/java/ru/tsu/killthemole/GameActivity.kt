package ru.tsu.killthemole

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity(){

    private val places = mutableListOf<Button>()
    private val holes = mutableListOf<Button>()
    private var moles = mutableListOf<Button>()
    private var polices = mutableListOf<Button>()

    private var collected = 0
    private var busted = 0

    private var bribeCost = 1
    private var score = 0L
    private var scoreToBeat = 0L
    private var maxScore = 0L

    private var speed = 0L
    private var difficulty = 0

    private val timers = mutableListOf<CountDownTimer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)
        initViews()

        val holesNum = intent.getIntExtra("holes", 8)
        val gameTime = intent.getLongExtra("time",30000L)
        speed = intent.getLongExtra("speed", 3000L)
        difficulty = intent.getIntExtra("difficulty", 2)

        //рандомно выбираются места для кустов
        places.shuffle()
        for(i in 0 until holesNum) {
            places[i].background = (ContextCompat.getDrawable(this, R.drawable.weed_again))
            holes.add(places[i])
            places[i].setOnClickListener(onHoleClickListener)
        }
        tvScore.text = 0.toString()

        //устанавливаются правила для прохождения уровня, можно вынести в настройки
        maxScore = (gameTime/speed)*difficulty
        scoreToBeat = (maxScore*8/10)

        startGame(gameTime)
    }

    private fun startGame(gameTime: Long){
        Toast.makeText(baseContext,"start",Toast.LENGTH_SHORT).show()
        //таймер для отсчета времени
        timers.add( object: CountDownTimer(gameTime, 1000){
            override fun onTick(p0: Long) {
                tvEstimatedTime.text = getString(R.string.tv_estimated_time_counter, (p0/1000))
            }

            override fun onFinish() {

            }
        })
        timers[0].start()
        //таймер для игровых шагов
        //нужен, потому что время tick`a может не совпадать с одной секундой
        timers.add( object: CountDownTimer(gameTime, speed){
            override fun onTick(p0: Long) {
                updateGameField()
                if(Random.nextInt(1,3) == 1) bribeCost++
            }

            override fun onFinish() {
                finishGame()
            }
        })
        timers[1].start()
    }

    private fun finishGame(){
        for(mole in moles){
            holes.add(mole)
            mole.background = ContextCompat.getDrawable(baseContext, R.drawable.weed_again)
        }
        moles = mutableListOf()

        val resultData = Intent()
        resultData.putExtra("isPassed",score >= scoreToBeat)
        setResult(Activity.RESULT_OK,resultData)
        updateRepositoryStats()
        showResults()
        finish()
    }

    //не справился с создаем диалогового окна (пока что)
    private fun showResults(){
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
    }

    private fun updateRepositoryStats(){
        Repository.add(Repository.ATTEMPTS,1)
        Repository.add(Repository.COLLECTED, collected)
        Repository.add(Repository.BUSTED, busted)
        if(score>=scoreToBeat) {
            Repository.add(Repository.SUCCESS,1)
        }
    }

    private fun updateGameField(){
        for(mole in moles){
            holes.add(mole)
            mole.background = ContextCompat.getDrawable(baseContext, R.drawable.weed_again)
        }
        for(police in polices){
            holes.add(police)
            police.background = ContextCompat.getDrawable(baseContext, R.drawable.weed_again)
        }
        polices = mutableListOf()
        moles = mutableListOf()

        holes.shuffle()
        for(i in 0 until difficulty) {
            holes[0].background = ContextCompat.getDrawable(baseContext, R.drawable.kos)
            moles.add(holes[0])
            holes.removeAt(0)
        }

        if(Random.nextInt(1,3) == 1){
            holes.shuffle()
            holes[0].background = ContextCompat.getDrawable(baseContext, R.drawable.police)
            polices.add(holes[0])
            holes.removeAt(0)
        }
    }

    private val onHoleClickListener = View.OnClickListener{
        if(moles.contains(it)){
            score++
            tvScore.text = score.toString()
            moles.remove(it)
            holes.add(it as Button)
            it.background = ContextCompat.getDrawable(this, R.drawable.weed_again)
            collected ++
        }
        if(polices.contains(it)){
            Toast.makeText(baseContext,"ты потерял $bribeCost очков", Toast.LENGTH_SHORT).show()
            score -= bribeCost
            tvScore.text = score.toString()
            polices.remove(it)
            holes.add(it as Button)
            it.background = ContextCompat.getDrawable(this, R.drawable.weed_again)
            busted++
        }
    }


    private fun initViews(){
        for(layouts in gameField.children){
            for(view in (layouts as LinearLayout).children) {
                places.add(view as Button)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        for(timer in timers){
            timer.cancel()
        }
    }
}