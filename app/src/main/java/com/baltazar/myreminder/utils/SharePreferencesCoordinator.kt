package com.baltazar.myreminder.utils

import android.content.Context
import android.preference.PreferenceManager

/**
 * @author Baltazar Rodriguez
 * @since v0.0.4
 */
class SharePreferencesCoordinator(private val mContext: Context) {

    fun saveUserName(userName: String) {
        val editor = getSharePreferences().edit()
        editor.putString(USER_NAME, userName)
        editor.apply()
    }

    fun getUserName() = getSharePreferences().getString(USER_NAME, "No Name")

    private fun getSharePreferences() = PreferenceManager.getDefaultSharedPreferences(mContext)

    companion object {
        const val USER_NAME = "user_name"
    }
}