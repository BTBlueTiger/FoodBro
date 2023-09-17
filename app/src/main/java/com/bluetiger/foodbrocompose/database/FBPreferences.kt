package com.bluetiger.foodbrocompose.database

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.bluetiger.foodbrocompose.main_activity.NavManager

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
        DESIRED_BARCODE,
        CURRENT_USER
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

    fun setDesiredOpenFoodFactsData(timestamp: Long) =
        getPreferenceEditor().putLong(PreferenceTask.DESIRED_BARCODE.name, timestamp).apply()
    fun getDesiredOpenFoodFactsData() = getPreferences().getLong(PreferenceTask.DESIRED_BARCODE.name, 0)

}

