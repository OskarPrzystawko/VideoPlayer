package project.video.player.utils

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import project.video.player.utils.ImageLoaderUtils.loadImage
import java.lang.Exception

object ImageLoaderUtils {

    fun ImageView.loadImage(url: String?, errorImageRes: Int? = null) {
        if (url != null) {
            loadImageIntoImageView(this, url, errorImageRes)
        } else if (errorImageRes != null) {
            this.setImageResource(errorImageRes)
        }
    }

    private fun loadImageIntoImageView(imageView: ImageView, url: String, errorImageRes: Int? = null) {
        Picasso.get()
                .load(url.httpToHttps())
                .into(imageView, object : Callback {
                    override fun onSuccess() = Unit
                    override fun onError(e: Exception?) {
                        errorImageRes?.let {
                            imageView.setImageResource(it)
                        }
                    }
                })
    }

    private fun String.httpToHttps(): String {
        val httpPrefix = "http"
        if (startsWith(httpPrefix)) {
            val urlWithoutHttp = this.removePrefix(httpPrefix)
            return "https$urlWithoutHttp"
        }
        return this
    }

}