package com.example.madcamp_androidproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PhotoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val sharedPreferences: SharedPreferences = getSharedPreferences("BookmarkPreferences", Context.MODE_PRIVATE)
        val gridView: GridView = findViewById(R.id.gridView)
        val BookmarkShowBtn : ImageButton = findViewById(R.id.GalleryShowBookmark)
        val DeleteShowBtn : ImageButton = findViewById(R.id.GalleryShowDelete)
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
            R.drawable.img21,
            R.drawable.img22,
            R.drawable.img23,
            R.drawable.img24,
            R.drawable.img25,
            R.drawable.img26,
            R.drawable.img27,
            R.drawable.img28,
            R.drawable.img29,
            R.drawable.img30,
            R.drawable.img31,
            R.drawable.img32,
            R.drawable.img33,
            R.drawable.img34,
            R.drawable.img35,
            R.drawable.img36,
            R.drawable.img37,
            R.drawable.img38,
            R.drawable.img39,
            // Add more image URLs as needed
        )


        val bin =
            getSharedPreferences("Trashcan", MODE_PRIVATE)
        val ShownImages = mutableListOf<Int>()
        for (i in 0 until images.size) {
            val imageid = images[i]
            val isDeleted = bin.getBoolean("img_$imageid", false)

            if (!isDeleted) {
                ShownImages.add(imageid)
            }
        }
        val adapter = ImageAdapter(this, ShownImages.toTypedArray())
        gridView.adapter = adapter
        val imgNum = adapter.count
        sharedPreferences.edit().putInt("imgCount", imgNum).apply()
        gridView.setOnItemClickListener { _, _, position, _ ->

            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra("imageID", ShownImages[position])
            intent.putIntegerArrayListExtra("images", ArrayList(ShownImages.toList()))
            startActivity(intent)
        }

        BookmarkShowBtn.setOnClickListener {
            val intent = Intent(this, BookmarkImageViewActivity::class.java)
            intent.putIntegerArrayListExtra("images", ArrayList(images.toList()))
            startActivity(intent)
            overridePendingTransition(0, 0);
        }

        DeleteShowBtn.setOnClickListener {
            val intent = Intent(this, TrashcanViewActivity::class.java)
            intent.putIntegerArrayListExtra("images", ArrayList(images.toList()))
            startActivity(intent)
            overridePendingTransition(0, 0);
        }

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavView.selectedItemId = R.id.navigation_photo
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
    }
}
