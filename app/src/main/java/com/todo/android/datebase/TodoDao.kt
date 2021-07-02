package com.todo.android.datebase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Item.ContentEntity)

    @Delete
    fun delete(entity: Item.ContentEntity)

    @Query("DELETE FROM todolist")
    fun deleteAll()

    @Query("SELECT * FROM todolist ORDER BY baseDate, time")
    fun findAll(): LiveData<List<Item.ContentEntity>>

    @Query("SELECT baseDate FROM todolist GROUP BY baseDate")
    fun getBaseDateAll(): List<Item.Calendar>
}