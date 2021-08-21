package com.todo.android.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Item.ContentEntity)

    @Delete
    fun delete(entity: Item.ContentEntity)

    @Update
    fun update(entity: Item.ContentEntity)

    @Query("DELETE FROM todolist")
    fun deleteAll()

    @Query("SELECT * FROM todolist WHERE `check` = 0 ORDER BY baseDate, time")
    fun findPendingJobs(): LiveData<List<Item.ContentEntity>>

    @Query("SELECT * FROM todoList WHERE `check` = 1 ORDER BY baseDate, time")
    fun findCompleteJobs(): LiveData<List<Item.ContentEntity>>

    @Query("SELECT baseDate FROM todolist GROUP BY baseDate")
    fun getBaseDateAll(): List<Item.Calendar>
}