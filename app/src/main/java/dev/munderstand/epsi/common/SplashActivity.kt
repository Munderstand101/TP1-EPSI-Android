package dev.munderstand.epsi.common

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import dev.munderstand.epsi.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val newIntent= Intent(application, HomeActivity::class.java)
            startActivity(newIntent)
            finish()
        },2000)

        // Change the logo for dark mode
        val logo = findViewById<ImageView>(R.id.splash_logo)
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            logo.setImageResource(R.drawable.dummy_logo_night)
        }

    }
}