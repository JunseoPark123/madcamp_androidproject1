package com.example.madcamp_androidproject

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FullScreenActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_IMAGE_ID = "extra_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        val fullScreenImageView: ImageView = findViewById(R.id.fullScreenImageView)

        val imageId = intent.getIntExtra(EXTRA_IMAGE_ID, 0)
        fullScreenImageView.setImageResource(imageId)
    }
}