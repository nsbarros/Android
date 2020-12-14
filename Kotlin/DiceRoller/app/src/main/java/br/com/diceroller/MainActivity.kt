package br.com.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rollButton = roll_button
        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        roll_text.text = (1..6).random().toString()
    }
}
