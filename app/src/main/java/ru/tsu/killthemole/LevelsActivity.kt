package ru.tsu.killthemole

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_levels.*

class LevelsActivity : AppCompatActivity(), View.OnClickListener {

    private val buttons = mutableListOf<Button>()
    private lateinit var levels: MutableList<LevelData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
        initButtons()
        initLevels()
        updateUI()
    }

    private fun updateUI() {
        val passed = Repository.getInt(Repository.LAST_PASSED,0)
        for (i in 0 until passed) {
            with(buttons[i]) {
                backgroundTintList = ColorStateList.valueOf(getColor(R.color.test_green))
                isClickable = true
            }
        }
        with(buttons[passed]) {
            isClickable = true
            backgroundTintList = null
        }
        for (i in passed + 1 until buttons.size) {
            with(buttons[i]) {
                backgroundTintList = ColorStateList.valueOf(getColor(R.color.test_red))
                isClickable = false
            }
        }
    }

    override fun onClick(p0: View?) {
        val intent = Intent(this, GameActivity::class.java)
        var isNew = 2
        with(intent) {
            for(i in buttons.indices){
                if(p0==buttons[i]){
                    putExtra("time", levels[i].time)
                    putExtra("holes", levels[i].holes)
                    putExtra("difficulty", levels[i].difficulty)
                    putExtra("speed", levels[i].speed)
                    if(i==Repository.getInt(Repository.LAST_PASSED, 0)){
                        isNew = 1
                    }
                }

            }
        }
        startActivityForResult(intent, isNew)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!!.getBooleanExtra("isPassed", false) && requestCode == 1) {
            val pas = Repository.getInt(Repository.LAST_PASSED,0) + 1
            Repository.putValue(Repository.LAST_PASSED to pas)
            updateUI()
        }
    }


    private fun initButtons() {
        buttons.add(button1)
        buttons.add(button2)
        buttons.add(button3)
        buttons.add(button4)
        buttons.add(button5)
        buttons.add(button6)
        buttons.add(button7)
        buttons.add(button8)
        buttons.add(button9)
        buttons.add(button10)
        buttons.add(button11)
        buttons.add(button12)
        for (button in buttons) {
            button.setOnClickListener(this)
        }

        btn_settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initLevels(){
        levels = mutableListOf()
        levels.add(LevelData(1,30000L,5000L, 2))
        levels.add(LevelData(1,30000L,5000L,4))
        levels.add(LevelData(2,30000L,5000L,5))
        levels.add(LevelData(2,32000L,4000L, 7))
        levels.add(LevelData(2,32000L,4000L, 8))
        levels.add(LevelData(3,30000L,3000L, 9))
        levels.add(LevelData(3,30000L,3000L, 10))
        levels.add(LevelData(4,30000L,2000L, 11))
        levels.add(LevelData(5,30000L,2000L, 12))
        levels.add(LevelData(6,30000L,1000L, 12))
        levels.add(LevelData(6,30000L,1000L, 13))
        levels.add(LevelData(7,30000L,1000L, 14))
    }
}