package br.com.quotesoftheday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var list: Array<String> = arrayOf(
        "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
        "Maecenas ornare eu augue nec commodo.",
        "Vivamus ullamcorper, urna quis vestibulum luctus, purus enim eleifend orci, tincidunt varius metus metus ac erat. Vivamus tellus nisl, sodales sed posuere vitae, porttitor non purus. Nunc aliquet, sem vel iaculis laoreet, nisl felis accumsan metus, a suscipit mi sem eget ligula. Integer iaculis et nisl vel placerat.",
        "Fusce at nibh eu tellus eleifend consequat.",
        "Proin nulla purus, accumsan sit amet metus sit amet, suscipit dictum urna.",
        "Donec dapibus velit in dui ornare, hendrerit euismod massa tristique."
    )
    private var sizeArray = list.size - 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button2.setOnClickListener {
            textView.text = getPhrase()
        }
    }

    fun getPhrase(): String {
        return list[Random.nextInt(sizeArray)]
    }
}
