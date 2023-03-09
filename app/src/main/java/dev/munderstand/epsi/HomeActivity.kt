package dev.munderstand.epsi

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        setHeaderTxt("Epsi")
        //showLeft()
        //showRight()

        val logo = findViewById<ImageView>(R.id.iv_homeLogo)
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            logo.setImageResource(R.drawable.dummy_logo_night)
            showNight()
        }

        val btnInfos = findViewById<Button>(R.id.btnInfo)
        btnInfos.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, GroupInfosActivity::class.java)
            startActivity(intent)
        })

    }
}