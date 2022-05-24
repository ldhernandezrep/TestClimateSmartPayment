package com.example.testclimatesmart.core

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.testclimatesmart.R


class Extensions {

    fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .error(R.drawable.storm)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

}