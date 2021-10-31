package com.example.thelynx.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun Context.loadImage(path: Any, imageView: ImageView) = Glide.with(this).load(path).into(imageView)