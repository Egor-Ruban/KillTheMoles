package ru.tsu.killthemole

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        updateUI()

        btn_reset.setOnClickListener {
            Repository.reset()
            updateUI()
        }

        btn_stats_exit.setOnClickListener {
            finish()
        }
    }

    private fun updateUI(){
        tv_stats_collected.text = "Косячков собрано: ${Repository.getInt(Repository.COLLECTED, 0)}"
        tv_stats_attempts.text = "Игр начато: ${Repository.getInt(Repository.ATTEMPTS,0)}"
        tv_stats_success.text = "Игр пройдено: ${Repository.getInt(Repository.SUCCESS,0)}"
        tv_stats_last.text = "Уровней пройдено: ${Repository.getInt(Repository.LAST_PASSED,0)}"
    }
}