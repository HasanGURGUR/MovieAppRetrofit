package hasan.gurgur.movieappexample

import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImageFromUrl(imageview: ImageView, url : String?) {
        val new_url = "https://image.tmdb.org/t/p/original$url"
        Glide.with(imageview.context).load(new_url).into(imageview)
    }
}