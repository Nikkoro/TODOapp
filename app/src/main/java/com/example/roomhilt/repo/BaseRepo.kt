package com.example.roomhilt.repo

import androidx.lifecycle.LiveData
import com.example.roomhilt.room.CustomEntity

interface BaseRepo {


    fun giveRepository() : String

    suspend fun  insert(customEntity : CustomEntity)

    suspend fun  update(customEntity : CustomEntity)

    suspend fun  delete(customEntity : CustomEntity)

    suspend fun  deleteAll()

    fun  getAll() : LiveData<List<CustomEntity>>
}