package com.example.thelynx.ui.extension

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.thelynx.R

fun filterOptionMenu(
    inflater: MenuInflater,
    menuRes: Int,
    menu: Menu,
    idView: Int,
    callBackClick: () -> Unit
) {
    inflater.inflate(menuRes, menu)
    val filterItem = menu.findItem(idView).actionView as AppCompatImageView
    filterItem.apply {
        setBackgroundResource(R.drawable.ic_filter)
        setOnClickListener {
            callBackClick.invoke()
        }
    }
}

fun Context.loadImage(path: Any, imageView: ImageView) = Glide.with(this).load(path).into(imageView)
