package com.example.rsandroidtask4.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.rsandroidtask4.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
     setPreferencesFromResource(R.xml.preferences,rootKey)
    }
}