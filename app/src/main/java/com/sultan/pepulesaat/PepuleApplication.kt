package com.sultan.pepulesaat

import android.app.Application
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.sultan.pepulesaat.data.room.database.FavoriteDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PepuleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        val db = Room.databaseBuilder(
            this,
            FavoriteDatabase::class.java,
            "favorite_db"
        ).build()
    }
}