package dev.munderstand.epsi.products

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.munderstand.epsi.common.BaseActivity
import dev.munderstand.epsi.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class CategoriesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setHeaderTxt("Rayons")
        showLeft()

        val logo = findViewById<ImageView>(R.id.iv_homeLogo)
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            logo.setImageResource(R.drawable.dummy_logo_night)
            showNight()
        }

        val categories = arrayListOf<Caregorie>()

        val rcvCategories = findViewById<RecyclerView>(R.id.rcv_categories)
        rcvCategories.layoutManager = LinearLayoutManager(this)
        val categorieAdapter = CategorieAdapter(categories,application)
        rcvCategories.adapter = categorieAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = "https://www.ugarit.online/epsi/categories.json"
        val request =
            Request.Builder().url(mRequestUrl).cacheControl(CacheControl.FORCE_NETWORK).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null) {
                    val jsCateg = JSONObject(data)
                    val jsArrayCateg = jsCateg.getJSONArray("items")

                    for (i in 0 until jsArrayCateg.length()) {
                        val jsCateg = jsArrayCateg.getJSONObject(i)
                        val categorie = Caregorie(
                            jsCateg.optString("title", "Not found"),
                            jsCateg.optString("products_url", "Not found"),
                        )
                        categories.add(categorie)
                    }
                    runOnUiThread(Runnable {
                        categorieAdapter.notifyDataSetChanged()
                    })

                    Log.e("WS", data)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread(Runnable {
                    Toast.makeText(application, e.message, Toast.LENGTH_SHORT).show()
                })
            }

        })

    }
}