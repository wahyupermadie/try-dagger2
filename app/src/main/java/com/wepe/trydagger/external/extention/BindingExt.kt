package com.wepe.trydagger.external.extention

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.wepe.trydagger.utils.Constants

object BindingExt {

    @JvmStatic
    @BindingAdapter("srcImage")
    fun setImage(view : AppCompatImageView, url : String){
        Glide.with(view.context)
            .asBitmap()
            .thumbnail(0.1f)
            .load(Constants.IMAGE_URL+url)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("textDate")
    fun setDate(view: AppCompatTextView, date: String){
        val newDate = Constants.dateFormat(date)
        view.text = newDate
    }
}