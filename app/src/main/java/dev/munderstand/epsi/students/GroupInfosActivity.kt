package dev.munderstand.epsi.students

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.munderstand.epsi.common.BaseActivity
import dev.munderstand.epsi.R
import org.json.JSONObject


class GroupInfosActivity : BaseActivity() {

    val data = "{\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"picture_url\": \"https://static.wikia.nocookie.net/lemondededisney/images/e/e0/Hakunamatata.jpg/revision/latest?cb=20151215190528&path-prefix=fr\",\n" +
            "      \"name\": \"ANCHIDIN Valentin\",\n" +
            "      \"email\": \"v.anchidin@epsi.fr\",\n" +
            "      \"city\": \"Bordeaux\",\n" +
            "      \"phone\": \"0619191919\",\n" +
            "      \"zipcode\": \"33000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"picture_url\": \"https://cdn-s-www.ledauphine.com/images/E67F1737-6644-4F5E-B301-4F4FCF5DBD41/NW_raw/homer-simpson-photo-archives-1380731474.jpg\",\n" +
            "      \"name\": \"DROSS-DENIS Maxence\",\n" +
            "      \"email\": \"maxence.drossdenis@epsi.fr\",\n" +
            "      \"city\": \"Bordeaux\",\n" +
            "      \"phone\": \"0619191919\",\n" +
            "      \"zipcode\": \"33000\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_infos)




        val students = arrayListOf<Student>()

        val jsStudents = JSONObject(data)
        val jsArrayStudent = jsStudents.getJSONArray("items")

        for (i in 0 until jsArrayStudent.length()) {
            val jsStudent = jsArrayStudent.getJSONObject(i)
            val student = Student(
                jsStudent.optString("name", "Not found"),
                jsStudent.optString("email", "Not found"),
                jsStudent.optString("picture_url", "Not found"),
                jsStudent.optString("phone", "Not found"),
                jsStudent.optString("city", "Not found"),
                jsStudent.optString("zipcode", "Not found")
            )
            students.add(student)
        }

        val recyclerviewStudents = findViewById<RecyclerView>(R.id.recyclerviewStudents)
        recyclerviewStudents.layoutManager = LinearLayoutManager(this)
        val studentAdapter = StudentAdapter(students,application)
        recyclerviewStudents.adapter = studentAdapter



        setHeaderTxt("Infos")
        showLeft()



        val logo = findViewById<ImageView>(R.id.iv_homeLogo)
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            logo.setImageResource(R.drawable.dummy_logo_night)
            showNight()
        }

    }


    private fun startStudentActivity(url: String, title: String, nom: String, prenom: String, email: String) {
        val intent = Intent(application, StudentInfoActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("title", title)
        intent.putExtra("nom", nom)
        intent.putExtra("prenom", prenom)
        intent.putExtra("email", email)
        startActivity(intent)
    }

}