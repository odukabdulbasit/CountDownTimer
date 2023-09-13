package com.odukabdulbasit.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 60000
    private val countDownInterval: Long = 1000

    private lateinit var startButton : Button
    private lateinit var stopButton : Button
    private lateinit var textViewTimer : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        textViewTimer = findViewById(R.id.textViewTimer)

        startButton.setOnClickListener {
            startTimer()
            startButton.visibility = View.GONE
            stopButton.visibility = View.VISIBLE
        }

        stopButton.setOnClickListener {
            stopTimer()
            startButton.visibility = View.VISIBLE
            stopButton.visibility = View.GONE
        }

        updateCountdownUI()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountdownUI()
            }

            override fun onFinish() {
                // Handle timer finish event here
                startButton.visibility = View.VISIBLE
                stopButton.visibility = View.GONE
                timeLeftInMillis = 60000
                updateCountdownUI()

            }
        }

        countDownTimer.start()
    }

    private fun stopTimer() {
        countDownTimer.cancel()
    }



    private fun updateCountdownUI() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60

        val timeRemainingText = "$minutes:${String.format("%02d", seconds)}"
        textViewTimer.text = timeRemainingText
    }
}
