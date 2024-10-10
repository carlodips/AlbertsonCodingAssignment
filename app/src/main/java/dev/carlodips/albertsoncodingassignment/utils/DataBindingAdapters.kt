package dev.carlodips.albertsoncodingassignment.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.apply {
        error = errorMessage
        isErrorEnabled = !errorMessage.isNullOrBlank()
    }
}

@BindingAdapter("imageUrl")
fun loadImage(shapeableImageView: ShapeableImageView, url: String) {
    if (url.isEmpty()) return

    Glide.with(shapeableImageView.context)
        .load(url)
        .into(shapeableImageView)
}