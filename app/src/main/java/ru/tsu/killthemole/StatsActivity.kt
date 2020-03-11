package ru.tsu.killthemole

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        updateUI()

        btnReset.setOnClickListener {
            Repository.reset()
            updateUI()
        }

        btnStatsExit.setOnClickListener {
            finish()
        }
    }

    private fun updateUI(){
        tvStatsBusted.text = getString(R.string.tv_stats_busted,
                Repository.getInt(Repository.BUSTED, 0))
        tvStatsCollected.text = getString(R.string.tv_stats_collected,
                Repository.getInt(Repository.COLLECTED, 0))
        tvStatsAttempts.text = getString(R.string.tv_stats_attempts,
                Repository.getInt(Repository.ATTEMPTS,0))
        tvStatsSuccess.text = getString(R.string.tv_stats_success,
                Repository.getInt(Repository.SUCCESS,0))
        tvStatsLast.text = getString(R.string.tv_stats_last,
                Repository.getInt(Repository.LAST_PASSED,0))
    }
}