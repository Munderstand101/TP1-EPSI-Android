package dev.munderstand.epsi.students

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dev.munderstand.epsi.R
import dev.munderstand.epsi.common.BaseActivity


class StudentInfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)


        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            showNight()
        }

        showLeft()
        showRight()

        val tvNomPrenom =findViewById<TextView>(R.id.tv_nomprenom)
        val tvEmail =findViewById<TextView>(R.id.tv_email)
        val tvGroupe =findViewById<TextView>(R.id.tv_groupe)

        val image=findViewById<ImageView>(R.id.iv_avatar)
        if(intent.extras!=null){

            val url=intent.extras!!.getString("avatar_url","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj0AEwRdUSWfs2LPDlLKn9kI-KvverDKfy0w&usqp=CAU");
            Picasso.get().load(url).into(image);

            val title=intent.extras!!.getString("nom_prenom","Epsi")
            setHeaderTxt(title)

            tvNomPrenom.text = intent.extras!!.getString("nom_prenom","Epsi")
            tvEmail.text = intent.extras!!.getString("email","contact@Hakuna-Matata.dev")
            tvGroupe.text = intent.extras!!.getString("groupe","Hakuna Matata !")
        }
    }

    fun onLinkClicked(view: View?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
        startActivity(intent)
    }



}