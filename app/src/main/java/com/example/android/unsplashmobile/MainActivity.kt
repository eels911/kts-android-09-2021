package com.example.android.unsplashmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.unsplashmobile.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d("MainActivity onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("MainActivity onStart()")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("MainActivity onResume()")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("MainActivity onPause()")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("MainActivity onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("MainActivity onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("MainActivity onDestroy()")
    }
}