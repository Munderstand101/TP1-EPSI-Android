package dev.munderstand.epsi.students

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

class StudentAdapter(val students: ArrayList<Student>, val application: Application) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.student_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students.get(position)
        holder.textViewName.text = student.name
        holder.contentLayout.setOnClickListener(View.OnClickListener {
            Toast.makeText(holder.contentLayout.context, student.name, Toast.LENGTH_SHORT).show()
            startStudentActivity(student.imgUrl,student.name,student.email,student.name)

        })
    }

    override fun getItemCount(): Int {
        return students.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val contentLayout = view.findViewById<LinearLayout>(R.id.contentLayout)
    }

    private fun startStudentActivity(url: String, nom_prenom: String, email: String,  group: String) {
        val intent = Intent(application, StudentInfoActivity::class.java)
        intent.putExtra("avatar_url", url)
        intent.putExtra("nom_prenom", nom_prenom)
        intent.putExtra("email", email)
        intent.putExtra("group", group)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(intent)
    }
}
