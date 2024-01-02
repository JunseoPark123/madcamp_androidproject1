package com.example.madcamp_androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import androidx.viewpager.widget.ViewPager
import android.widget.ImageButton
import android.content.Context


class FullScreenActivity : AppCompatActivity() {

    companion object {
        const val Image_ID = "imageId"
        const val PREF_NAME = "MyPreferences"
        const val KEY_IS_FAVORITE = "isFavorite"
        const val KEY_FAVORITE_PHOTOS = "favoritePhotos"
    }

    private lateinit var viewPager: ViewPager
    private lateinit var fullScreenImageView: ImageView
    private var isFavorite = false
    private lateinit var BookmarkButton: ImageButton

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
            val Mainpage = Intent(this, PhotoActivity::class.java)
            startActivity(Mainpage)
        }

        BookmarkButton = view.findViewById(R.id.GalleryBookmarkImage)
        isFavorite = loadFavoriteStatus(imageUrl)

        // 버튼의 초기 상태 설정
        updateFavoriteButtonState()

        BookmarkButton.setOnClickListener {
            isFavorite = !isFavorite

            // 버튼의 색상 및 아이콘 변경
            updateFavoriteButtonState()

            // 즐겨찾기 상태 저장
            //saveFavoriteStatus(imageUrl, isFavorite)

            // 즐겨찾기한 사진들 목록 저장
            if (isFavorite) {
                //images.add(imageUrl)
            } else {
                //images.remove(imageUrl)
            }
            //saveFavoritePhotos(images.toSet())
        }

    }

    private fun updateFavoriteButtonState() {
        if (isFavorite) {
            BookmarkButton.setImageResource(R.drawable.ic_bookmark_filled)
        } else {
            BookmarkButton.setImageResource(R.drawable.ic_bookmark_border)
        }
    }

    private fun loadFavoriteStatus(imageId: Int): Boolean {
        val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("$KEY_IS_FAVORITE$imageId", false)
    }


}