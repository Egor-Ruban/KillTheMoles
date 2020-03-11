package ru.tsu.killthemole

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    var holes = 8
    var time = 60000L
    var speed = 2000L
    var difficulty = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        //добавить генерацию уровней (не добавил, формулы не придумал)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        btnStartGame.setOnClickListener(this)
        initSeekBars()

        tvHoles.text = getString(R.string.holes, holes)
        tvTime.text = getString(R.string.time, getPluralSeconds((time/1000)))
        tvSpeed.text = getString(R.string.Speed, getPluralSeconds(speed/1000))
        tvDifficulty.text = getString(R.string.Difficulty, difficulty)
    }

    override fun onClick(p0: View?) {
        if(p0 == btnStartGame){
            if(holes<difficulty){
                Toast.makeText(this,"Убедитесь, что сложность не превышает колличество ям",
                    Toast.LENGTH_SHORT).show()
            } else {
                startGame()
            }
        }
    }

    private fun startGame(){
        val gameIntent = Intent(this, GameActivity::class.java)
        gameIntent.putExtra("holes", holes)
        gameIntent.putExtra("time", time)
        gameIntent.putExtra("speed", speed)
        gameIntent.putExtra("difficulty", difficulty)
        startActivityForResult(gameIntent,1)
    }

    private fun initSeekBars(){
        sbHoles.setOnSeekBarChangeListener(this)
        sbTime.setOnSeekBarChangeListener(this)
        sbSpeed.setOnSeekBarChangeListener(this)
        sbDifficulty.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        when(p0){
            sbHoles -> {
                sbDifficulty.max = p1
                holes = p1+1
                tvHoles.text = getString(R.string.holes, holes)
            }
            sbTime ->{
                time = (p1+1)*10000L
                tvTime.text = getString(R.string.time, getPluralSeconds((p1+1)*10L))
            }
            sbSpeed ->{
                speed = (p1+1)*1000L
                tvSpeed.text = getString(R.string.Speed, getPluralSeconds(p1+1L))
            }
            sbDifficulty ->{
                difficulty = p1+1
                tvDifficulty.text = getString(
                        R.string.DifficultyWithString,
                        getPluralHoles(difficulty))
            }
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    private fun getPluralSeconds(value : Long) : String{
        var typeOfPlural = 3
        if((value%100L)/10L!=1L && value%10L == 1L) typeOfPlural = 1
        else if((value%100L)/10L!=1L && value%10 >=2 && value%10 <=4) typeOfPlural = 2
        return when(typeOfPlural){
            1 -> "$value секунда"
            2 -> "$value секунды"
            3 -> "$value секунд"
            else -> "problem"
        }
    }

    private fun getPluralHoles(value:Int):String{
        var typeOfPlural = 3
        if((value%100L)/10L!=1L && value%10L == 1L) typeOfPlural = 1
        else if((value%100L)/10L!=1L && value%10 >=2 && value%10 <=4) typeOfPlural = 2
        return when(typeOfPlural){
            1 -> "$value яма"
            2 -> "$value ямы"
            3 -> "$value ям"
            else -> "problem"
        }
    }
}