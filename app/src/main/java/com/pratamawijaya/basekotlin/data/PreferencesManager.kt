package com.pratamawijaya.basekotlin.data

import android.content.Context
import android.content.SharedPreferences
import com.github.ajalt.timberkt.d
import com.pratamawijaya.basekotlin.di.ApplicationContext
import javax.inject.Inject

/**
 * Created by pratama on 10/11/17.
 */
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPref: SharedPreferences

    companion object {
        val PREF_NAME = "baseapp"
        val PREF_USERNAME = "username"
        val PREF_USER_IS_LOGIN = "userislogin"
        val PREF_TOKEN = "auth_token"
    }

    init {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        sharedPref.edit().clear().apply()
    }

    fun saveString(key: String, value: String) {
        d { "save $value to $key" }
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(key: String): String = sharedPref.getString(key, "")

    fun saveInt(key: String, value: Int) {
        d { "save $value to $key" }
        val editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getInt(key: String): Int = sharedPref.getInt(key, 0)

    fun getBoolean(key: String): Boolean = sharedPref.getBoolean(key, false)

    fun saveBoolean(key: String, value: Boolean) {
        d { "save $value to $key" }
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getUserLogin(): Boolean = getBoolean(PREF_USER_IS_LOGIN)

    fun setUserLogin(login: Boolean) {
        saveBoolean(PREF_USER_IS_LOGIN, login)
    }

    fun getAuthToken(): String? {
        val authToken = "Bearer ${getString(PREF_TOKEN)}"
        d { "auth token $authToken" }
        return authToken
    }

    fun userLogout() {
        d { "user logout" }
        clear()
    }
}