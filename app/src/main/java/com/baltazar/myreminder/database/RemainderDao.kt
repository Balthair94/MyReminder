package com.baltazar.myreminder.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface that manage the interaction with the database
 *
 * Data Access Objects or DAOs are used to access our data when we implement Room.
 * Each DAO have to include a set of methods to manipulate the data (insert, update, delete or get).
 *
 * @author Baltazar Rodriguez
 * @since v0.0.5
 */
@Dao
interface RemainderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRemainder(remainder: RemainderEntity): Completable

    @Delete
    fun deleteRemainder(remainder: RemainderEntity): Completable

    @Update
    fun updateRemainder(remainder: RemainderEntity): Completable

    @Query("SELECT * FROM remainder WHERE id == :id")
    fun getRemainderById(id: Int): Single<RemainderEntity>

    @Query("SELECT * FROM remainder ORDER BY date(schedule_date) DESC")
    fun getRemainders(): Single<List<RemainderEntity>>

    @Query("DELETE FROM remainder WHERE id == :id")
    fun deleteRemainderById(id: Int): Completable
}