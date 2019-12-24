package com.master8.experimentalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    override fun onResume() {
        super.onResume()

        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }
}
