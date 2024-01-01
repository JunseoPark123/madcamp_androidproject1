package com.example.madcamp_androidproject

import android.content.Intent
import android.widget.AdapterView
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import android.widget.AbsListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView: GridView = findViewById(R.id.gridView)

        val images = arrayOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
            R.drawable.img14,
            R.drawable.img15,
            R.drawable.img16,
            R.drawable.img17,
            R.drawable.img18,
            R.drawable.img19,
            R.drawable.img20,
            // Add more image URLs as needed
        )

        val adapter = ImageAdapter(this, images)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            // Handle item click, e.g., open a larger view of the image
            val selectedImage = adapter.getItem(position)

            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra("imageID", images[position])
            intent.putIntegerArrayListExtra("images", ArrayList(images.toList()))
            startActivity(intent)
        }
    }
}
