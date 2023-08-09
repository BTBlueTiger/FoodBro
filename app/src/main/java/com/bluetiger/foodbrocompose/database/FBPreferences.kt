package com.bluetiger.foodbrocompose.database

import android.content.Context

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
    private var context : Context? = null
    private var filename : String? = null
    private val applicationContext
        get() = context!!
    private val requireFileName
        get() = filename!!


    enum class PreferenceTask{
        USER_IS_SET, USER_EMAIL
    }

    companion object : SimpleSingletonHolder<FBPreferences>(::FBPreferences)

    fun initiate(n: String, c: Context){
        context = c
        filename = n
    }

    private fun getPreferences() =
        applicationContext.getSharedPreferences(filename, Context.MODE_PRIVATE)

    private fun getPreferenceEditor() =
        getPreferences().edit()

    fun isUserSet() =
        getPreferences().getBoolean(PreferenceTask.USER_IS_SET.name, false)
    fun setUserIsSet(boolean: Boolean) =
        getPreferenceEditor().putBoolean(PreferenceTask.USER_IS_SET.name, boolean).apply()

    fun getUserEmail() =
        getPreferences().getString(PreferenceTask.USER_EMAIL.name, "")
    fun setUserEmail(email: String) =
        getPreferenceEditor().putString(PreferenceTask.USER_EMAIL.name, email)
}