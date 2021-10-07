package com.example.testappforwork.ui.images

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testappforwork.R
import com.example.testappforwork.models.Image
import com.example.testappforwork.utilities.downloadAndSetImage
import kotlin.math.roundToInt

abstract class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(image: Image)
}

class ImageViewHolder(view: View) : AppViewHolder(view) {
    private val imageContent = view.findViewById<ImageView>(R.id.content_image_view)
    private val author = view.findViewById<TextView>(R.id.author_text_view)

    override fun bind(image: Image) {
        imageContent.downloadAndSetImage(image.downloadUrl)

        // надо доработать, при первоначальной загрузке не определяет размер первых элементов
        if (imageContent.width != 0) {
            val scale = image.width.toDouble() / image.height.toDouble()
            imageContent.layoutParams.height = (imageContent.width / scale).roundToInt()
        }
        author.text = image.author
    }
}

class LoadingViewHolder(view: View) : AppViewHolder(view) {
    override fun bind(image: Image) {}
}
