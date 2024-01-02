package com.example.madcamp_androidproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageAdapter(private val context: Context, private val images: Array<Int>) : BaseAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            val size = calculateImageSize(context)
            val params = ViewGroup.LayoutParams(size, size)
            imageView.layoutParams = params
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }

        Glide.with(context)
            .load(images[position])
            .into(imageView)

        return imageView
    }

    private fun calculateImageSize(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val horizontalSpacing = 4.dpToPx() // 여백 설정
        val imageSize = (screenWidth - (3 * horizontalSpacing)) / 4 // 4개의 이미지가 들어가도록 설정
        return imageSize
    }

    private fun Int.dpToPx(): Int {
        val scale = context.resources.displayMetrics.density
        return (this * scale).toInt()
    }

}
