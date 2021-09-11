package com.example.rsandroidtask4.ui.settings

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

data class UserPreferences(
    val usesDatabase: UsesDatabase,
    val sortOrder: SortOrder
)

enum class SortOrder {
    BY_NAME,
    BY_SURNAME,
    BY_AGE,
    BY_POSITION,
    BY_EXPERIENCE
}

enum class UsesDatabase {
    ROOM,
    CURSOR
}

private const val TAG = "UserPreferencesRepo"

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {


    private object PreferencesKeys {
        val SORT_ORDER = stringPreferencesKey("sort")
        val USES_DATABASE = stringPreferencesKey("database")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { preferences ->
            // Get the sort order from preferences and convert it to a [SortOrder] object
            val sortOrder =
                SortOrder.valueOf(
                    preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.BY_NAME.name
                )

            // Get our databaseManagementSystem, default ROOM if not set:
            val database =
                UsesDatabase.valueOf(
                    preferences[PreferencesKeys.USES_DATABASE] ?: UsesDatabase.ROOM.name
                )
            UserPreferences(database, sortOrder)
        }

    suspend fun sortMethod(){
        dataStore.edit { preferences ->
            val currentOrder = SortOrder.valueOf(
                preferences[PreferencesKeys.SORT_ORDER]?: SortOrder.BY_NAME.name
            )
           when(currentOrder){
                SortOrder.BY_NAME -> preferences[PreferencesKeys.SORT_ORDER] = SortOrder.BY_NAME.name
                SortOrder.BY_SURNAME -> preferences[PreferencesKeys.SORT_ORDER] = SortOrder.BY_SURNAME.name
                SortOrder.BY_AGE -> preferences[PreferencesKeys.SORT_ORDER] = SortOrder.BY_AGE.name
                SortOrder.BY_POSITION -> preferences[PreferencesKeys.SORT_ORDER] = SortOrder.BY_POSITION.name
                SortOrder.BY_EXPERIENCE -> preferences[PreferencesKeys.SORT_ORDER] = SortOrder.BY_EXPERIENCE.name
            }
        }
    }

    suspend fun currentDBMS(){
        dataStore.edit{ preferences ->
            val currentDatabase = UsesDatabase.valueOf(
                preferences[PreferencesKeys.USES_DATABASE]?:UsesDatabase.ROOM.name
            )
            when(currentDatabase){
                UsesDatabase.ROOM -> preferences[PreferencesKeys.USES_DATABASE] =UsesDatabase.ROOM.name
                UsesDatabase.CURSOR -> preferences[PreferencesKeys.USES_DATABASE] =UsesDatabase.CURSOR.name
            }

        }
    }
}
