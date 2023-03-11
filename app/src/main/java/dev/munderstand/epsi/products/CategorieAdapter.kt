package dev.munderstand.epsi.products

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dev.munderstand.epsi.R
import dev.munderstand.epsi.students.StudentInfoActivity

class CategorieAdapter(val categories: ArrayList<Caregorie>, val application: Application) :
    RecyclerView.Adapter<CategorieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.categories_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categ = categories.get(position)
        holder.tvCategName.text = categ.title
        holder.contentLayout.setOnClickListener(View.OnClickListener {
            Toast.makeText(holder.contentLayout.context, categ.title, Toast.LENGTH_SHORT).show()
            startStudentActivity(categ.title,categ.products_url)

        })
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategName = view.findViewById<TextView>(R.id.tv_categName)
        val contentLayout = view.findViewById<LinearLayout>(R.id.contentLayout)
    }

    private fun startStudentActivity(title: String, products_url: String) {
        val intent = Intent(application, ProductsListActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("products_url", products_url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(intent)
    }
}
