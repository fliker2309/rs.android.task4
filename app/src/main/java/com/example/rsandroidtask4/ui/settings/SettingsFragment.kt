package com.example.rsandroidtask4.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.presentation.list.ListViewModel
import com.example.rsandroidtask4.presentation.list.ListViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

class SettingsFragment : PreferenceFragmentCompat() {

    @InternalCoroutinesApi
    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory()
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
     setPreferencesFromResource(R.xml.preferences,rootKey)
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbms = preferenceManager.findPreference<ListPreference>("dbms_")
    }*/
}