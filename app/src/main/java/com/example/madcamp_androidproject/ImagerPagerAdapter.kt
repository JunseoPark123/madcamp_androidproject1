package com.example.madcamp_androidproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageButton
import android.widget.Button
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import android.content.SharedPreferences
import androidx.cardview.widget.CardView

class ImagePagerAdapter(private val images: List<Int>, private val context: Context) : PagerAdapter() {


    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("BookmarkPreferences", Context.MODE_PRIVATE)
    private val imgs: SharedPreferences =
        context.getSharedPreferences("ImageIDs", Context.MODE_PRIVATE)
    private val bin: SharedPreferences =
        context.getSharedPreferences("Trashcan", Context.MODE_PRIVATE)
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.image_full_screen, container, false)

        val imageView = view.findViewById<ImageView>(R.id.fullScreenImageView)
        val bookmarkButton = view.findViewById<ImageButton>(R.id.GalleryBookmarkImage)
        container.addView(view)
        Glide.with(context)
            .load(images[position])
            .into(imageView)

        bookmarkButton.setOnClickListener {
            val isBookmarked = isBookmarkSelected(images[position])
            saveBookmarkStatus(images[position], !isBookmarked)
            updateBookmarkState(bookmarkButton, images[position])
        }
        updateBookmarkState(bookmarkButton, images[position])

        val DeleteBtn : ImageButton = view.findViewById(R.id.GalleryDeleteImage)
        val deleteConfirmationCardView : CardView = view.findViewById(R.id.deleteConfirmationCardView)
        val darkOverlay : View = view.findViewById(R.id.darkOverlay)
        val buttonCancelDelete : Button = view.findViewById(R.id.buttonCancelDelete)
        val buttonConfirmDelete : Button = view.findViewById(R.id.buttonConfirmDelete)
        DeleteBtn.setOnClickListener {
            deleteConfirmationCardView.visibility = View.VISIBLE
            darkOverlay.visibility = View.VISIBLE
            bookmarkButton.isClickable = false
        }
        buttonCancelDelete.setOnClickListener{
            deleteConfirmationCardView.visibility = View.GONE
            darkOverlay.visibility = View.GONE
            bookmarkButton.isClickable = true
        }

        buttonConfirmDelete.setOnClickListener{
            val curImg = images[position]
            sharedPreferences.edit().putBoolean("img_$curImg", false).apply()
            bin.edit().putBoolean("img_$curImg", true).apply()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    private fun isBookmarkSelected(imageId: Int): Boolean {
        return sharedPreferences.getBoolean("img_$imageId", false)
    }

    private fun updateBookmarkState(bookmarkButton: ImageButton, imageId: Int) {
        val isBookmarked = isBookmarkSelected(imageId)
        if (isBookmarked) {
            bookmarkButton.setImageResource(R.drawable.baseline_favorite_red_24)
        } else {
            bookmarkButton.setImageResource(R.drawable.baseline_favorite_border_24)
        }
    }

    private fun saveBookmarkStatus(imageId: Int, isBookmarked: Boolean) {
        sharedPreferences.edit().putBoolean("img_$imageId", isBookmarked).apply()
        imgs.edit().putInt("imgId_$imageId", imageId).apply()
    }
}
