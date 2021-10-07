package com.example.testappforwork.ui.images.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.testappforwork.models.Image
import kotlin.collections.ArrayList

class ImageDiffUtilCallback(
    private var oldItems: ArrayList<Image>,
    private var newItems: ArrayList<Image>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].downloadUrl == newItems[newItemPosition].downloadUrl

}