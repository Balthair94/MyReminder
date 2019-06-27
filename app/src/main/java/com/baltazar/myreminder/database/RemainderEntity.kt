package com.baltazar.myreminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * This class is the representation of a table in our local database. We can also consider it as a model object representation
 * of a Remainder
 *
 * @author Baltazar Rodriguez
 * @since v0.0.5
 */
@Entity(tableName = "remainder")
data class RemainderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "schedule_date")
    val scheduleDate: Date
)