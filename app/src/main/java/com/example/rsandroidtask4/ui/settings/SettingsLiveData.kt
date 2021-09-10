package com.example.rsandroidtask4.ui.settings

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

class SettingsLiveData (private val sharedPreferences : SharedPreferences): LiveData<Int>(){

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener{ sharedPreferences, key ->
        if(key == "sort"){
            value = sharedPreferences.getString(key,"0")?.toInt()

        }

        override fun onActive() {
            value = sharedPreferences.getString("sort","0")?.toInt()
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
            super.onActive()
        }

        override fun onInactive() {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
            super.onInactive()
        }


    }
}