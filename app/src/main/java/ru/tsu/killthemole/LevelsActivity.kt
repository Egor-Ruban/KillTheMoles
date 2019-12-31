package ru.tsu.killthemole

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_levels.*

class LevelsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        initButtons()
    }

    override fun onClick(p0: View?) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("time", 30000L)
        with(intent) {
            when (p0) {
                button1 -> {
                    putExtra("holes", 2)
                    putExtra("difficulty", 1)
                    putExtra("speed", 5000L)
                }
                button2 -> {
                    putExtra("holes", 4)
                    putExtra("difficulty", 1)
                    putExtra("speed", 5000L)
                }
                button3 -> {
                    putExtra("holes", 5)
                    putExtra("difficulty", 2)
                    putExtra("speed", 5000L)
                }
                button4 -> {
                    putExtra("holes", 7)
                    putExtra("difficulty", 2)
                    putExtra("speed", 4000L)
                }
                button5 -> {
                    putExtra("holes", 8)
                    putExtra("difficulty", 2)
                    putExtra("speed", 4000L)
                }
                button6 -> {
                    putExtra("holes", 8)
                    putExtra("difficulty", 3)
                    putExtra("speed", 3000L)
                }
                button7 -> {
                    putExtra("holes", 11)
                    putExtra("difficulty", 3)
                    putExtra("speed", 3000L)
                }
                button8 -> {
                    putExtra("holes", 12)
                    putExtra("difficulty", 4)
                    putExtra("speed", 2000L)
                }
                button9 -> {
                    putExtra("holes", 12)
                    putExtra("difficulty", 5)
                    putExtra("speed", 2000L)
                }
                button10 -> {
                    putExtra("holes", 12)
                    putExtra("difficulty", 6)
                    putExtra("speed", 1000L)
                }
                button11 -> {
                    putExtra("holes", 13)
                    putExtra("difficulty", 6)
                    putExtra("speed", 1000L)
                }
                button12 -> {
                    putExtra("holes", 14)
                    putExtra("difficulty", 7)
                    putExtra("speed", 1)
                }
                else ->{}
            }
        }
        startActivity(intent)
    }

    private fun initButtons(){
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button10.setOnClickListener(this)
        button11.setOnClickListener(this)
        button12.setOnClickListener(this)

        btn_settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}