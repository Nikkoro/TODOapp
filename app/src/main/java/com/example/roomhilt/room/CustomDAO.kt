package com.example.roomhilt.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(customEntity : CustomEntity)

    @Update
    fun update(customEntity: CustomEntity)



    @Query("DELETE FROM custom_table WHERE Id = :id")
    fun delete(id : Int?)

    @Query("DELETE FROM custom_table")
    fun deleteAll()

    //@Query("SELECT * FROM custom_table")
    @Query("SELECT * FROM custom_table ORDER BY Id ASC")
    fun getAll() : LiveData<List<CustomEntity>>

}