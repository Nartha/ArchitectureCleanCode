package com.example.thelynx.utils

import android.content.Context
import androidx.core.content.edit
import com.example.thelynx.BuildConfig
import com.example.thelynx.utils.Constant.PREFERENCES_FIRST_INSTALL
import com.example.thelynx.utils.Constant.PREFERENCES_STATUS_LIKE

class SharedPreference(context: Context) {
    private val sharedPref = context.applicationContext.getSharedPreferences(
        BuildConfig.APPLICATION_ID,
        Context.MODE_PRIVATE
    )

    var clickLike
    get() = sharedPref.getBoolean(PREFERENCES_STATUS_LIKE, false)
    set(value) {
        sharedPref.edit {
            putBoolean(PREFERENCES_STATUS_LIKE, value)
        }
    }
}