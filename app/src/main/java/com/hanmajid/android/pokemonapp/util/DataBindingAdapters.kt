package com.hanmajid.android.pokemonapp.util

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.TooltipCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hanmajid.android.pokemonapp.R

/**
 * Sets an [ImageView]'s source by the given [url].
 */
@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
            .load(it)
            .placeholder(R.color.black_10)
            .error(R.drawable.unknown)
            .into(view)
    }
}

/**
 * Sets the visibility of a [View] by the given [isHidden].
 */
@BindingAdapter("hidden")
fun setHidden(view: View, isHidden: Boolean?) {
    view.visibility = if (isHidden == true) View.GONE else View.VISIBLE
}

/**
 * Sets the view's tooltip text.
 */
@BindingAdapter("tooltipTextCompat")
fun setTooltipTextCompat(view: View, tooltipText: String) {
    TooltipCompat.setTooltipText(view, tooltipText)
}