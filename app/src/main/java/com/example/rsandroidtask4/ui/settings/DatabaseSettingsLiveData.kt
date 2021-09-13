package com.example.rsandroidtask4.ui.settings

import android.content.SharedPreferences
import androidx.compose.runtime.key
import androidx.lifecycle.LiveData

class DatabaseSettingsLiveData (private val sharedPreferences: SharedPreferences) : LiveData<Int>() {

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener{sharedPreferences, key ->
        if (key == "database"){
            value = sharedPreferences.getString(key, "0")?.toInt()
        }
    }

    override fun onActive() {
        super.onActive()
        value = sharedPreferences.getString("database", "0")?.toInt()
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}