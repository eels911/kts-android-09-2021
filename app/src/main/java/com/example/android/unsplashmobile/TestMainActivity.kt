package com.example.android.unsplashmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class TestMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("TestActivity onCreate completed")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("TestActivity onStart completed")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("TestActivity onResume completed")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("TestActivity onPause completed")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("TestActivity onStop completed")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("TestActivity onRestart completed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("TestActivity onDestroy completed")
    }
}