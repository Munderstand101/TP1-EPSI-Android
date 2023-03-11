package dev.munderstand.epsi.common

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import dev.munderstand.epsi.students.GroupInfosActivity
import dev.munderstand.epsi.R
import dev.munderstand.epsi.products.CategoriesActivity

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setHeaderTxt("Epsi")

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

        val btnProducts = findViewById<Button>(R.id.btnProducts)
        btnProducts.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, CategoriesActivity::class.java)
            startActivity(intent)
        })

    }
}