package com.example.madcamp_androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class FullScreenBookmark : AppCompatActivity() {
    companion object {
        const val Image_ID = "imageId"
        const val PREF_NAME = "MyPreferences"
        const val KEY_IS_FAVORITE = "isFavorite"
        const val KEY_FAVORITE_PHOTOS = "favoritePhotos"
    }

    private lateinit var viewPager: ViewPager
    private lateinit var fullScreenImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)


        viewPager = findViewById(R.id.viewPager)
        val view = layoutInflater.inflate(R.layout.image_full_screen, null)
        fullScreenImageView = view.findViewById(R.id.fullScreenImageView)


        val images = intent.getIntegerArrayListExtra("images") ?: emptyList()
        val adapter = BookmarkPagerAdapter(images, this)
        viewPager.adapter = adapter


        val imageUrl = intent.getIntExtra("imageID", 0)
        val position = images.indexOf(imageUrl)
        viewPager.currentItem = position


        val btnGoBack: ImageButton = findViewById(R.id.GalleryFullScreenBackbutton)

        btnGoBack.setOnClickListener {
            val intent = Intent(this, BookmarkImageViewActivity::class.java)
            intent.putIntegerArrayListExtra("images", ArrayList(images.toList()))
            startActivity(intent)
        }
    }
}