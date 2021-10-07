package com.example.testappforwork.ui.images.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testappforwork.R
import com.example.testappforwork.models.Image
import com.example.testappforwork.utilities.downloadAndSetImage
import kotlin.math.roundToInt

abstract class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(image: Image)
}

class ImageViewHolder(view: View) : AppViewHolder(view) {
    private val imageContent = view.findViewById<ImageView>(R.id.content_image_view)
    private val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
    private val tryAgainButton = view.findViewById<Button>(R.id.try_again_button)
    private val author = view.findViewById<TextView>(R.id.author_text_view)

    override fun bind(image: Image) {
        downloadImage(image)
        author.text = image.author
    }

    private fun downloadImage(image: Image) {
        tryAgainButton.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        Glide.with(imageContent.context)
            .load(image.downloadUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    tryAgainButton.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    tryAgainButton.setOnClickListener {
                        downloadImage(image)
                    }
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    tryAgainButton.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(imageContent)

        //imageContent.downloadAndSetImage(image.downloadUrl)

        // надо доработать, при первоначальной загрузке не определяет размер первых элементов
        if (imageContent.width != 0) {
            val scale = image.width.toDouble() / image.height.toDouble()
            imageContent.layoutParams.height = (imageContent.width / scale).roundToInt()
        }
    }
}

class LoadingViewHolder(view: View) : AppViewHolder(view) {
    override fun bind(image: Image) {}
}
