package com.example.madcamp_androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ImageButton
import android.widget.TextView

class TrashcanViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trashcan_view)

        val gridView: GridView = findViewById(R.id.gridView)
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
        val BookmarkShowBtn : ImageButton = findViewById(R.id.GalleryShowBookmark)
        val GalleryShowBtn : ImageButton = findViewById(R.id.GalleryShowAllImages)
        val noTrashText: TextView = findViewById(R.id.noTrashText)

        val bin = getSharedPreferences("Trashcan", MODE_PRIVATE)
        val ShownImages = mutableListOf<Int>()
        for (i in 0 until images.size) {
            val imageid = images[i]
            val isDeletedForever = bin.getBoolean("imgF_$imageid", false)
            val isDeleted = bin.getBoolean("img_$imageid", false)

            if (isDeleted and !isDeletedForever) {
                ShownImages.add(imageid)
            }
        }


        if (ShownImages.isEmpty()) {
            noTrashText.visibility = View.VISIBLE
        } else {
            noTrashText.visibility = View.GONE
        }

        val adapter =ImageAdapter( this, ShownImages.toTypedArray())
        gridView.adapter = adapter
        gridView.setOnItemClickListener { _, _, position, _ ->

            val intent = Intent(this, FullScreenTrash::class.java)
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

        GalleryShowBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0);
        }
    }
}