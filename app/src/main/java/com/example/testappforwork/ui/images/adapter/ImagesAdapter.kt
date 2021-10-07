package com.example.testappforwork.ui.images.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testappforwork.R
import com.example.testappforwork.models.Image

class ImagesAdapter: RecyclerView.Adapter<AppViewHolder>() {
    private var images = ArrayList<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.image_item -> ImageViewHolder(view)
            R.layout.loading_item -> LoadingViewHolder(view)
            else -> TODO()
        }
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    override fun getItemViewType(position: Int): Int {
        return when (images[position].id) {
            LOADING -> R.layout.loading_item
            else -> R.layout.image_item
        }
    }

    fun updateList(images: ArrayList<Image>) {
        val result = DiffUtil.calculateDiff(ImageDiffUtilCallback(this.images, images))
        result.dispatchUpdatesTo(this)
        this.images.clear()
        this.images.addAll(images)
    }

    fun addLoadingHeader() {
        images.add(Image(id = LOADING))
        notifyItemInserted(images.size)
    }

    companion object {
        private const val TAG = "ImagesAdapter"
        private const val LOADING = "loading"
    }
}