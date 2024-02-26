package com.example.pruebatecnicaedgarpina

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pruebatecnicaedgarpina.databinding.LaunchScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntroScreen : AppCompatActivity() {

    private val dispatcher: CoroutineDispatcher by lazy { Dispatchers.IO }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: LaunchScreenBinding = LaunchScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToMenu()
    }

    private fun navigateToMenu() {
        lifecycleScope.launch(dispatcher) {
            delay(2000)
            val launch = Intent(this@IntroScreen, MainActivity::class.java)
            startActivity(launch)
        }
    }
}