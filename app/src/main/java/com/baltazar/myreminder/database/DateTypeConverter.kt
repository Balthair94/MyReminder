package com.baltazar.myreminder.database

import androidx.room.TypeConverter
import java.util.Date

/**
 * @author Baltazar Rodriguez
 * @since v0.0.5
 */
class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}