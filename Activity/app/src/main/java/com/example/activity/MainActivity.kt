package com.example.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var goToButton: Button
    private lateinit var textViewResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this@MainActivity, "onStart Called", Toast.LENGTH_SHORT).show()

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Constants.RESULT_CODE) {
                val message = it.data!!.getStringExtra(Constants.INTENT_MESSAGE2_KEY)
                textViewResult.text = message
            }
        }

        goToButton = findViewById(R.id.button_go_to_activity)
        textViewResult = findViewById(R.id.textView)

        goToButton.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra(Constants.INTENT_MESSAGE_KEY, "Hello, from first Activity!")
            intent.putExtra(Constants.INTENT_MESSAGE2_KEY, "How was your day?")
            intent.putExtra(Constants.INTENT_DATA_NUMBER, 3.14)
            //startActivity(intent)
            getResult.launch(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this@MainActivity, "onStart Called", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this@MainActivity, "onResumed Called", Toast.LENGTH_SHORT).show()

    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this@MainActivity, "onPause Called", Toast.LENGTH_SHORT).show()

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this@MainActivity, "onStop Called", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this@MainActivity, "onDestroy Called", Toast.LENGTH_SHORT).show()
    }
}