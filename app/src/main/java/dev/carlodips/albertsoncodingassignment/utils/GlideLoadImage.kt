package dev.carlodips.albertsoncodingassignment.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("imageUrl")
fun loadImage(shapeableImageView: ShapeableImageView, url: String) {
    if (url.isEmpty()) return

    Glide.with(shapeableImageView.context)
        .load(url)
        .into(shapeableImageView)
}