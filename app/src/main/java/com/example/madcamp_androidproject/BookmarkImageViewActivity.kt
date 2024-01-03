package com.example.madcamp_androidproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookmarkImageViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark_image_view)

        val gridView: GridView = findViewById(R.id.gridView)
        val AllImagesShowBtn: ImageButton = findViewById(R.id.GalleryShowAllImages)
        val noImageText: TextView = findViewById(R.id.noImageText)
        val DeleteShowBtn : ImageButton = findViewById(R.id.GalleryShowDelete)

        val imageslist = intent.getIntegerArrayListExtra("images") ?: emptyList()
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
            val intent = Intent(this, PhotoActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0);
        }

        DeleteShowBtn.setOnClickListener {
            val intent = Intent(this, TrashcanViewActivity::class.java)
            intent.putIntegerArrayListExtra("images", ArrayList(imageslist.toList()))
            startActivity(intent)
            overridePendingTransition(0, 0);
        }
    }

    private fun getBookmarkedImages(): List<Int> {
        val sharedPreferences =
            getSharedPreferences("BookmarkPreferences", MODE_PRIVATE)
        val imgs: SharedPreferences =
            getSharedPreferences("ImageIDs", Context.MODE_PRIVATE)

        //val imgNum = sharedPreferences.getInt("imgCount", 0)

        val bookmarkedImages = mutableListOf<Int>()

        val imagesL = arrayOf(
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

        if (imagesL.isNotEmpty()) {
            for (i in 0 until imagesL.size) {
                val imageid = imagesL[i]
                val isBookmarked = sharedPreferences.getBoolean("img_$imageid", false)
                if (isBookmarked) {
                    bookmarkedImages.add(imageid)
                }
            }
        }

        return bookmarkedImages
    }
}