package br.com.meunumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            TextViewNumber.text = getNumber()
        }
    }

    private fun getNumber() = "Your number is ${Random.nextInt(0, 61)}"
}