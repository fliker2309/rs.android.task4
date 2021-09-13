/*
package com.example.rsandroidtask4.data.db.repository

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
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

private const val TAG = "DataStoreRepository"
const val PREFERENCE_NAME = "user_preference"

class DataStoreRepository(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCE_NAME
    )

    private object PreferencesKeys {
        val SORT_ORDER = preferencesKey<String>("sort")
        val USES_DATABASE = preferencesKey<String>("database")
    }

    val dataStoreFlow: Flow<UserPreferences> = dataStore.data
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

    suspend fun saveSortMethod() {
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

    suspend fun saveDatabaseImplementation(){
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


*/
