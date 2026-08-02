package com.prince.scheduler

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 96, 48, 48)
        }

        val info = TextView(this).apply {
            text = "Scheduler is ready.\n\n" +
                "1. Grant the microphone permission if asked.\n" +
                "2. Tap below and set Scheduler as your Digital assistant app.\n" +
                "3. Hold the power button to start a voice capture."
            textSize = 16f
        }

        val openSettings = Button(this).apply {
            text = "Open assistant settings"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_VOICE_INPUT_SETTINGS))
            }
        }

        layout.addView(info)
        layout.addView(openSettings)
        setContentView(layout)
    }
}
