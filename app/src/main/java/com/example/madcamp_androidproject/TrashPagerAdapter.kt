package com.example.madcamp_androidproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class TrashPagerAdapter(private val images: List<Int>, private val context: Context) : PagerAdapter() {


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
        val view = inflater.inflate(R.layout.trash_full_screem, container, false)

        val imageView = view.findViewById<ImageView>(R.id.fullScreenImageView)
        val restoreButton = view.findViewById<ImageButton>(R.id.GalleryRestoreImage)
        val deleteforeverButton = view.findViewById<ImageButton>(R.id.GalleryDeleteForeverImage)

        container.addView(view)
        Glide.with(context)
            .load(images[position])
            .into(imageView)

        val deleteforeverConfirmationCardView : CardView = view.findViewById(R.id.deleteforeverConfirmationCardView)
        val restoreConfirmationCardView : CardView = view.findViewById(R.id.restoreConfirmationCardView)
        val darkOverlay : View = view.findViewById(R.id.darkOverlay)
        val buttonCancelDeleteForever : Button = view.findViewById(R.id.buttonCancelDeleteForever)
        val buttonConfirmDeleteForever : Button = view.findViewById(R.id.buttonConfirmDeleteForever)
        val buttonCancelRestore : Button = view.findViewById(R.id.buttonCancelRestore)
        val buttonConfirmRestore : Button = view.findViewById(R.id.buttonConfirmRestore)

        deleteforeverButton.setOnClickListener {
            deleteforeverConfirmationCardView.visibility = View.VISIBLE
            darkOverlay.visibility = View.VISIBLE
            restoreButton.isClickable = false
            deleteforeverButton.isClickable = false
        }

        buttonCancelDeleteForever.setOnClickListener{
            deleteforeverConfirmationCardView.visibility = View.GONE
            darkOverlay.visibility = View.GONE
            restoreButton.isClickable = true
            deleteforeverButton.isClickable = true
        }

        buttonConfirmDeleteForever.setOnClickListener{
            val curImg = images[position]
            bin.edit().putBoolean("imgF_$curImg", true).apply()
            bin.edit().putBoolean("img_$curImg", true).apply()
            val intent = Intent(context, TrashcanViewActivity::class.java)
            context.startActivity(intent)
        }

        restoreButton.setOnClickListener{
            restoreConfirmationCardView.visibility = View.VISIBLE
            darkOverlay.visibility = View.VISIBLE
            restoreButton.isClickable = false
            deleteforeverButton.isClickable = false
        }

        buttonCancelRestore.setOnClickListener{
            restoreConfirmationCardView.visibility = View.GONE
            darkOverlay.visibility = View.GONE
            restoreButton.isClickable = true
            deleteforeverButton.isClickable = true
        }

        buttonConfirmRestore.setOnClickListener{
            val curImg = images[position]
            bin.edit().putBoolean("img_$curImg", false).apply()
            bin.edit().putBoolean("imgF_$curImg", false).apply()
            val intent = Intent(context, TrashcanViewActivity::class.java)
            context.startActivity(intent)
        }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

}