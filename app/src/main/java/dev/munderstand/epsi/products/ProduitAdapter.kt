package dev.munderstand.epsi.products

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.munderstand.epsi.R


class ProduitAdapter(val produits: ArrayList<Produit>, val application: Application) :
    RecyclerView.Adapter<ProduitAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.produits_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = produits.get(position)
        holder.tvProductName.text = product.name
        holder.tvDesc.text = product.description
        Picasso.get().load(product.picture_url).into(holder.ivProduct)
        holder.contentLayout.setOnClickListener(View.OnClickListener {
            Toast.makeText(holder.contentLayout.context, product.name, Toast.LENGTH_SHORT).show()
            startStudentActivity(product.name,product.picture_url,product.description)

        })
    }

    override fun getItemCount(): Int {
        return produits.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProductName = view.findViewById<TextView>(R.id.tv_ProductName)
        val tvDesc = view.findViewById<TextView>(R.id.tv_Desc)
        val ivProduct = view.findViewById<ImageView>(R.id.iv_Product)
        val contentLayout = view.findViewById<LinearLayout>(R.id.contentLayout)
    }

    private fun startStudentActivity(name: String, picture_url: String, description: String) {
        val intent = Intent(application, ProductDetailsActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("picture_url", picture_url)
        intent.putExtra("description", description)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(intent)
    }
}
