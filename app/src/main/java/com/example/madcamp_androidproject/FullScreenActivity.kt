package com.example.madcamp_androidproject

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import androidx.viewpager.widget.ViewPager
import android.widget.ImageButton


class FullScreenActivity : AppCompatActivity() {

    companion object {
        const val Image_ID = "imageId"
    }

    private lateinit var viewPager: ViewPager
    private lateinit var fullScreenImageView: ImageView
    private lateinit var gestureDetector: GestureDetector
    private lateinit var scaleGestureDetector: ScaleGestureDetector

    private var scaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)


        viewPager = findViewById(R.id.viewPager)
        val view = layoutInflater.inflate(R.layout.image_full_screen, null)
        fullScreenImageView = view.findViewById(R.id.fullScreenImageView)

        val images = intent.getIntegerArrayListExtra("images") ?: emptyList()
        val adapter = ImagePagerAdapter(images, this)
        viewPager.adapter = adapter


        val imageUrl = intent.getIntExtra("imageID", 0)
        val position = images.indexOf(imageUrl)
        viewPager.currentItem = position


        val btnGoBack: Button = findViewById(R.id.GalleryFullScreenBackbutton)

        btnGoBack.setOnClickListener {
            val Mainpage = Intent(this, MainActivity::class.java)
            startActivity(Mainpage)
        }

        val BookmarkButton: ImageButton = findViewById(R.id.GalleryBookmarkImage)
        var isFavorite = false

        BookmarkButton.setOnClickListener {
            isFavorite = !isFavorite

            // 버튼의 색상 및 아이콘 변경
            if (isFavorite) {
                BookmarkButton.setImageResource(R.drawable.ic_bookmark_filled)
            } else {
                BookmarkButton.setImageResource(R.drawable.ic_bookmark_border)
            }
        }

        gestureDetector = GestureDetector(this, GestureListener())
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        fullScreenImageView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            scaleGestureDetector.onTouchEvent(event)
            true
        }
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            scaleFactor = if (scaleFactor == 1.0f) 2.0f else 1.0f
            fullScreenImageView.scaleX = scaleFactor
            fullScreenImageView.scaleY = scaleFactor
            return true
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = Math.max(1.0f, Math.min(scaleFactor, 3.0f)) // Restrict the scale range
            fullScreenImageView.scaleX = scaleFactor
            fullScreenImageView.scaleY = scaleFactor
            return true
        }
    }

}