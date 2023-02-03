package com.devlee.lunchpicker.util

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceUtil @Inject constructor(
    private val pref: SharedPreferences
) {

    companion object {
        const val IGNORE_STORE = "ignore_store"
    }

    fun setIgnoreStore(ignoreList: Collection<String>) {
        pref.edit {
            putString(IGNORE_STORE, ignoreList.toJsonNotNull())
        }
    }

    fun getIgnoreStore(): List<String> {
        return pref.getString(IGNORE_STORE, null).fromJson() ?: listOf()
    }


}