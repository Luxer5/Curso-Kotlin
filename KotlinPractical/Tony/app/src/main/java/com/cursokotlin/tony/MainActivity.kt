package com.cursokotlin.tony

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun doInference(inputString: String): Float {
        val inputVal = FloatArray(1)
        //inputVal[0] = inputString.toFloat()
        inputVal[0] = inputString.toFloat()

        val output = Array(1) { FloatArray(1) }
        interpreter.run(inputVal, output)
        return output[0][0]
    }
}