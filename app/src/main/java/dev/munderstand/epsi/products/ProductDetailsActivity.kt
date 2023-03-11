package dev.munderstand.epsi.products

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dev.munderstand.epsi.R
import dev.munderstand.epsi.common.BaseActivity

class ProductDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            showNight()
        }

        showLeft()
        showRight()

        val tvDescription =findViewById<TextView>(R.id.tv_description)
        val image=findViewById<ImageView>(R.id.iv_image)

        if(intent.extras!=null){
            val url=intent.extras!!.getString("picture_url","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj0AEwRdUSWfs2LPDlLKn9kI-KvverDKfy0w&usqp=CAU");
            Picasso.get().load(url).into(image);

            val title=intent.extras!!.getString("name","Nom du produit")
            setHeaderTxt(title)

            tvDescription.text = intent.extras!!.getString("description","Hakuna Matata !")
        }

    }
}