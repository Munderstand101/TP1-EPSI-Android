package dev.munderstand.epsi

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Epsi","################## onCreate :"+javaClass.simpleName)
    }

    override fun onStart() {
        super.onStart()
        Log.i("Epsi","################## onStart :"+javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Log.i("Epsi","################## onResume :"+javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.i("Epsi","################## onPause :"+javaClass.simpleName)
    }

    override fun onStop() {
        super.onStop()
        Log.i("Epsi","################## onStop :"+javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Epsi","################## onDestroy :"+javaClass.simpleName)
    }

    fun setHeaderTxt(txt: String) {
        val textViewTitle = findViewById<TextView>(R.id.tv_title)
        textViewTitle.setText(txt)
    }

    fun showLeft() {
        val iv_left = findViewById<ImageView>(R.id.iv_Left_Arrow)
        iv_left.visibility = View.VISIBLE
        iv_left.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun showRight() {
        val imageViewBack = findViewById<ImageView>(R.id.iv_Right_Arrow)
        imageViewBack.visibility = View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }


    fun showNight() {
        val tv_title = findViewById<TextView>(R.id.tv_title)
        tv_title.setTextColor(resources.getColor(R.color.white))


        val imageViewBack = findViewById<ImageView>(R.id.iv_Left_Arrow)
        imageViewBack.visibility = View.VISIBLE
        imageViewBack.setImageDrawable(resources.getDrawable(R.drawable.ic_prev_night))
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }


}

