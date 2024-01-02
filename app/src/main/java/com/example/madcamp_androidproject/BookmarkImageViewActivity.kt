package com.example.madcamp_androidproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.GridView
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class BookmarkImageViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark_image_view)

        val gridView: GridView = findViewById(R.id.gridView)
        val AllImagesShowBtn: ImageButton = findViewById(R.id.GalleryShowAllImages)
        val noImageText: TextView = findViewById(R.id.noImageText)

        val images = getBookmarkedImages().toTypedArray()

        if (images.isEmpty()) {
            noImageText.visibility = View.VISIBLE
        } else {
            noImageText.visibility = View.GONE
        }

        val adapter = ImageAdapter(this, images)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            // Handle item click, e.g., open a larger view of the image

            val intent = Intent(this, FullScreenBookmark::class.java)
            intent.putExtra("imageID", images[position])
            intent.putIntegerArrayListExtra("images", ArrayList(images.toList()))
            startActivity(intent)
        }

        AllImagesShowBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0);
        }
    }

    private fun getBookmarkedImages(): List<Int> {
        val sharedPreferences =
            getSharedPreferences("BookmarkPreferences", MODE_PRIVATE)
        val imgs: SharedPreferences =
            getSharedPreferences("ImageIDs", Context.MODE_PRIVATE)

        val imgNum = sharedPreferences.getInt("imgCount", 0)

        val bookmarkedImages = mutableListOf<Int>()

        val imageslist = intent.getIntegerArrayListExtra("images") ?: emptyList()

        for (i in 0 until imgNum) {
            val imageid = imageslist[i]
            val isBookmarked = sharedPreferences.getBoolean("img_$imageid", false)
            //Log.d("AdapterImages", isBookmarked.toString())
            if (isBookmarked) {
                val imageId = imgs.getInt("imgId_$imageid", 0)
                bookmarkedImages.add(imageId)
            }
        }

        return bookmarkedImages
    }
}