package dev.munderstand.epsi.products

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.munderstand.epsi.R
import dev.munderstand.epsi.common.BaseActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ProductsListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)

        showLeft()

        setHeaderTxt(intent.extras!!.getString("title","Epsi"))

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {

            showNight()
        }

        val produits = arrayListOf<Produit>()

        val rcvProduits = findViewById<RecyclerView>(R.id.rcv_products)
        rcvProduits.layoutManager = LinearLayoutManager(this)
        val produitAdapter = ProduitAdapter(produits,application)
        rcvProduits.adapter = produitAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = intent.extras!!.getString("products_url","Epsi")
        val request =
            Request.Builder().url(mRequestUrl).cacheControl(CacheControl.FORCE_NETWORK).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null) {
                    val jsProduit = JSONObject(data)
                    val jsArrayProduit = jsProduit.getJSONArray("items")

                    for (i in 0 until jsArrayProduit.length()) {
                        val jsProduit = jsArrayProduit.getJSONObject(i)
                        val produit = Produit(
                            jsProduit.optString("name", "Not found"),
                            jsProduit.optString("description", "Not found"),
                            jsProduit.optString("picture_url", "Not found"),
                        )
                        produits.add(produit)
                    }
                    runOnUiThread(Runnable {
                        produitAdapter.notifyDataSetChanged()
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