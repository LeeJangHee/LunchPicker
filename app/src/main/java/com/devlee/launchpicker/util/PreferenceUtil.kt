package com.devlee.launchpicker.util

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.devlee.launchpicker.util.Consts.TAG
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceUtil @Inject constructor(
    private val pref: SharedPreferences
) {

    private val gson = Gson()

    companion object {
        const val IGNORE_STORE = "ignore_store"
    }

    fun setIgnoreStore(ignoreList: Collection<String>) {
        Log.d(TAG, "setIgnoreStore: ${ignoreList.toPrettyJson()}")
        pref.edit {
            putString(IGNORE_STORE, ignoreList.toJsonNotNull())
        }
    }

    fun getIgnoreStore(): List<String> {
        return pref.getString(IGNORE_STORE, null).fromJson() ?: listOf()
    }


}