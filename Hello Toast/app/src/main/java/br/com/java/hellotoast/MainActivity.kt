package br.com.java.hellotoast

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListerner()
    }

    private fun initListerner() {
        button_label_toast.setOnClickListener {
            showToast()
        }
        button_label_count.setOnClickListener {
            countUp()
        }
    }

    private fun countUp() {
        mCount?.let {
            mCount++
            show_count.text = mCount.toString()
        }
    }

    private fun showToast() {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show()
    }
}
