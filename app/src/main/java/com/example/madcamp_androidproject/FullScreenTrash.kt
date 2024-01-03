package com.example.madcamp_androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager

class FullScreenTrash : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var fullScreenImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        viewPager = findViewById(R.id.viewPager)
        val view = layoutInflater.inflate(R.layout.trash_full_screem, null)
        fullScreenImageView = view.findViewById(R.id.fullScreenImageView)


        val images = intent.getIntegerArrayListExtra("images") ?: emptyList()
        val adapter = TrashPagerAdapter(images, this)
        viewPager.adapter = adapter


        val imageUrl = intent.getIntExtra("imageID", 0)
        val position = images.indexOf(imageUrl)
        viewPager.currentItem = position


        val btnGoBack: ImageButton = findViewById(R.id.GalleryFullScreenBackbutton)

        btnGoBack.setOnClickListener {
            val intent = Intent(this, TrashcanViewActivity::class.java)
            intent.putIntegerArrayListExtra("images", ArrayList(images.toList()))
            startActivity(intent)
        }
    }
}