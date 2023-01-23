package com.devlee.launchpicker.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun <T: Any> T.toPrettyJson(): String? {
    val gson = Gson().newBuilder().setPrettyPrinting().create()
    return gson.toJson(this, object : TypeToken<T>() {}.type)
}