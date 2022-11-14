package com.cursokotlin.lifecycle

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mediaPlayer=MediaPlayer().create(this, R.raw.ai_2)
        //mediaPlayer?.start()

        Log.i("LifeCycle", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("LifeCycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LifeCycle", "onResume")
    }

    override fun onPause() {
        super.onPause()

        finish()

    }

    override fun onStop() {
        super.onStop()
        Log.i("LifeCycle", "onStop")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LifeCycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "onDestroy")
    }
}