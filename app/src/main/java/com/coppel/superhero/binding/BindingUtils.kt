package com.coppel.superhero.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.coppel.superhero.R
import com.coppel.superhero.enums.ApiServiceStatus
import com.coppel.superhero.models.Hero

@BindingAdapter("imageUrl")
fun bindImage(imageView: AppCompatImageView, hero: Hero) {
    val requestOptions = RequestOptions()
        .placeholder(R.drawable.ic_broken_image)
        .error(R.drawable.ic_broken_image)

    hero.image.imageUrl.let {
        Glide.with(imageView.context)
            .load(it)
            .apply(requestOptions)
            .timeout(600)
            .into(imageView)
    }
}

@BindingAdapter("heroAliases")
fun bindText(textView: AppCompatTextView, hero: Hero) {
    var aliases = ""

    for (alias in hero.biography.aliases) {
        aliases += alias
    }

    aliases = if (aliases == "") "No aliases found." else aliases
    textView.text = aliases
}

@BindingAdapter("heroApiStatus")
fun bindStatus(imageView: AppCompatImageView, status: ApiServiceStatus?) {
    when (status) {
        ApiServiceStatus.NO_INTERNET_CONNECTION-> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_signal_wifi_bad)
        }
        ApiServiceStatus.NONE-> {
            imageView.visibility = View.VISIBLE
        }
        ApiServiceStatus.ERROR-> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_outline_error)
        }
        else -> {
            imageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("heroApiStatus")
fun bindStatus(textView: AppCompatTextView, status: ApiServiceStatus?) = when (status) {
    ApiServiceStatus.NONE -> {
        textView.visibility = View.VISIBLE
    }
    ApiServiceStatus.ERROR -> {
        textView.visibility = View.VISIBLE
        textView.setText("Error...")
    }
    ApiServiceStatus.NO_INTERNET_CONNECTION -> {
        textView.visibility = View.VISIBLE
        textView.setText("Error: No Internet Connection...")
    }
    else -> {
        textView.visibility = View.GONE
    }
}

@BindingAdapter("heroApiStatusProgress")
fun bindStatus(swipeRefreshLayout: SwipeRefreshLayout, status: ApiServiceStatus?) {
    when (status) {
        ApiServiceStatus.LOADING -> {
            swipeRefreshLayout.isRefreshing = true
        }
        else -> {
            swipeRefreshLayout.isRefreshing = false
        }
    }
}