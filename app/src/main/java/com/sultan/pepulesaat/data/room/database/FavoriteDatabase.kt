package com.sultan.pepulesaat.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sultan.pepulesaat.data.room.dao.FavoriteDao
import com.sultan.pepulesaat.data.room.entity.FavoriteEntity

@Database(
    exportSchema = false,
    entities = [FavoriteEntity::class],
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val dao: FavoriteDao
}