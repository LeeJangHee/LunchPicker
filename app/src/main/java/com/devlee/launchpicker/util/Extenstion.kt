package com.devlee.launchpicker.util

import android.content.res.Resources
import androidx.annotation.RestrictTo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun <T: Any> T.toPrettyJson(): String? {
    val gson = Gson().newBuilder().setPrettyPrinting().create()
    return gson.toJson(this, object : TypeToken<T>() {}.type)
}

fun <T: Any> String?.fromJson(): T? {
    val typeToken = object : TypeToken<T?>() {}.type
    return Gson().fromJson(this, typeToken)
}

fun <T: Any> String?.fromJsonNotNull(): T {
    return checkNotNull(this.fromJson()) { "Gson.fromJson is Null" }
}

fun <T: Any> T?.toJson(): String? {
    return Gson().toJson(this)
}

fun <T: Any> T?.toJsonNotNull(): String {
    return checkNotNull(this.toJson()) { "Gson.toJson is Null" }
}

/** Convert px to dp */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Float.dp(): Float = (this * Resources.getSystem().displayMetrics.density)

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Int.dp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Float.toDp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Int.toDp(): Float = (this * Resources.getSystem().displayMetrics.density)