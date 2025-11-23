package com.example.anothercoolapplication.features.userlist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.anothercoolapplication.databinding.ActivityLandingBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Launch a coroutine to simulate loading
        lifecycleScope.launch {
            // Wait for 4 seconds
            delay(4000)

            // After the delay, hide the progress bar and show the button
            binding.loadingProgressBar.visibility = View.GONE
            binding.nextButton.visibility = View.VISIBLE
        }

        binding.nextButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}