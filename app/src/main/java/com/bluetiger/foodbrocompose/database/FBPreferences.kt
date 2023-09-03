package com.bluetiger.foodbrocompose.database

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

open class SimpleSingletonHolder<out T : Any>(private val creator: () -> T) {
    @Volatile
    private var instance: T? = null

    fun getInstance(): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator()
                instance = created
                created
            }
        }
    }
}

class FBPreferences private constructor() {
    private var context: Context? = null
    private var filename: String? = null
    private val applicationContext
        get() = context!!
    private val requireFileName
        get() = filename!!


    enum class PreferenceTask {
        USER_IS_SET, USER_EMAIL
    }

    companion object : SimpleSingletonHolder<FBPreferences>(::FBPreferences)

    fun initiate(n: String, c: Context) {
        context = c
        filename = n
    }

    private fun getPreferences() =
        applicationContext.getSharedPreferences(filename, Context.MODE_PRIVATE)

    private fun getPreferenceEditor() =
        getPreferences().edit()

    private val _userIsSet = mutableStateOf(false)
    val userIsSet: State<Boolean> = _userIsSet


    fun setUserIsSet(boolean: Boolean) {
        FBPreferences.getInstance().getPreferenceEditor()
            .putBoolean(PreferenceTask.USER_IS_SET.name, boolean).apply()
        _userIsSet.value = true
    }

    fun getUserIsSet() =
        FBPreferences.getInstance().getPreferences()
            .getBoolean(PreferenceTask.USER_IS_SET.name, false)

    fun setUser(userEmail: String) {
        FBPreferences.getInstance().getPreferenceEditor()
            .putString(PreferenceTask.USER_EMAIL.name, userEmail)
            .apply()
    }

    fun getUser(): String {
        return FBPreferences.getInstance().getPreferences()
            .getString(PreferenceTask.USER_EMAIL.name, "") ?: ""
    }

    interface IPreferenceAble {


        fun setUserIsSet(boolean: Boolean) {
            FBPreferences.getInstance().getPreferenceEditor()
                .putBoolean(PreferenceTask.USER_IS_SET.name, boolean).apply()
        }

        fun getUserIsSet() =
            FBPreferences.getInstance().getPreferences()
                .getBoolean(PreferenceTask.USER_IS_SET.name, false)

        fun setUser(userEmail: String) {
            FBPreferences.getInstance().getPreferenceEditor()
                .putString(PreferenceTask.USER_EMAIL.name, userEmail)
                .apply()
        }

        fun getUser() {
            FBPreferences.getInstance().getPreferences()
                .getString(PreferenceTask.USER_EMAIL.name, "")
        }
    }

}

