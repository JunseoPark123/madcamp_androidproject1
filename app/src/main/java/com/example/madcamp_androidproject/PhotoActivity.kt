package com.example.madcamp_androidproject

import android.content.Intent
import android.widget.AdapterView
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.squareup.picasso.Picasso

class PhotoActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val gridView: GridView = findViewById(R.id.gridView)

        val imageUrls = arrayOf(
            "https://cdn.pixabay.com/photo/2021/08/03/07/03/orange-6518675_960_720.jpg",
            "https://cdn.pixabay.com/photo/2023/12/15/21/47/cat-8451431_1280.jpg",
            "https://cdn.pixabay.com/photo/2023/12/05/17/45/plant-8432181_1280.jpg",
            "https://cdn.pixabay.com/photo/2023/11/29/11/55/pine-hills-8419433_1280.jpg",
            "https://cdn.pixabay.com/photo/2023/07/23/13/04/flower-8145077_1280.jpg",
            "https://cdn.pixabay.com/photo/2023/10/05/17/54/geese-8296524_1280.jpg",
            "https://cdn.pixabay.com/photo/2023/12/04/17/24/sandpiper-8429874_1280.jpg",
            "https://cdn.pixabay.com/photo/2016/12/03/15/44/fireworks-1880045_1280.jpg",
            "https://cdn.pixabay.com/photo/2023/10/19/21/08/sunset-8327637_1280.jpg",
            // Add more image URLs as needed
        )

        val adapter = ImageAdapter(this, imageUrls)
        gridView.adapter = adapter
        //adapter.notifyDataSetChanged()

        gridView.setOnItemClickListener { _, _, position, _ ->
            // Handle item click, e.g., open a larger view of the image
            val selectedImage = adapter.getItem(position) as Int

            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra(FullScreenActivity.EXTRA_IMAGE_ID, selectedImage)
            startActivity(intent)
        }

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_phonenumber -> {
                    val intent = Intent(this, ContactActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_photo -> {
                    true
                }

                R.id.navigation_english -> {
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
        bottomNavView.selectedItemId = R.id.navigation_photo

    }
}
