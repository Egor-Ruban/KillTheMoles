package ru.tsu.killthemole

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_levels.*

class LevelsActivity : AppCompatActivity() {

    private val buttons = mutableListOf<Button>()
    private lateinit var levels: MutableList<LevelSettings>

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!!.getBooleanExtra("isPassed", false) && requestCode == 1) {
            val pas = Repository.getInt(Repository.LAST_PASSED,0) + 1
            Repository.putValue(Repository.LAST_PASSED to pas)
            updateUI()
        }
    }


    private fun initButtons() {
        for(view in linearLayout2.children){
            if(view is Button) {
                buttons.add(view)
                view.setOnClickListener { val intent = Intent(this, GameActivity::class.java)
                    var isNew = 2
                    with(intent) {
                        for(i in buttons.indices){
                            if(it==buttons[i]){
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
                    startActivityForResult(intent, isNew) }
            }
        }
        buttons.remove(btnSettings)

        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initLevels(){
        levels = mutableListOf()
        val difficulties = arrayOf(1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7)
        val times = arrayOf(30000L, 30000L, 30000L, 32000L, 32000L, 30000L,
                30000L, 30000L, 30000L, 30000L, 30000L, 30000L)
        val speeds = arrayOf(5000L, 5000L, 5000L, 4000L, 4000L, 3000L,
                3000L, 2000L, 2000L, 1000L, 1000L, 1000L)
        val holes = arrayOf( 24, 5, 7, 8, 9, 10, 11, 12, 12, 13, 14)
        for(i in difficulties.indices){
            levels.add(LevelSettings(
                    difficulties[i],
                    times[i],
                    speeds[i],
                    holes[i]
            ))
        }
    }
}