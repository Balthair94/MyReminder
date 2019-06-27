package com.baltazar.myreminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * This class is the one that creates the database and provides with a way of interaction with the Dao's
 * @author Baltazar Rodriguez
 * @since v0.0.5
 */
@Database(entities = [RemainderEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun remainderDao(): RemainderDao

    companion object {
        var INSTANCE: AppDataBase? = null

        fun init(context: Context) {
            synchronized(AppDataBase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "MyRemainderDB").build()
            }
        }

        fun destroyDataBaseInstance() {
            INSTANCE = null
        }
    }
}