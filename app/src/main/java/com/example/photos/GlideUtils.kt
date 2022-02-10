package com.example.photos

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideUtils {

    fun loadImage(
        activity: Activity?, url: String?,
        imageView: ImageView?, progressBar: ProgressBar?
    ) {
        if (activity != null && !activity.isFinishing) {
            Glide.with(activity).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (progressBar != null) progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (progressBar != null) progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(imageView!!)
        }
    }

    fun loadImageAsBitmap(
        activity: Activity?, url: String?,
        imageView: ImageView?, placeholder: Int
    ) {
        if (activity != null && !activity.isFinishing) {
            Glide.with(activity).asBitmap().load(url).placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView!!)
        }
    }

    fun loadImage(
        context: Context?, url: String?,
        imageView: ImageView?, placeholder: Int
    ) {
        if (context != null) {
            Glide.with(context).load(url).placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView!!)
        }
    }
}