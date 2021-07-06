package com.example.roomhilt.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.roomhilt.model.CustomModel
import com.example.roomhilt.room.CustomEntity

object ConvertList {

    private fun toListModel(customEntity: List<CustomEntity>) : MutableList<CustomModel> {
        val itemList : MutableList<CustomModel> = mutableListOf<CustomModel>()
        customEntity.map {
            itemList.add(
                CustomModel( it.id?:0, it.title?:"", it.desc?:"", it.date?:"")
            )
        }
        return itemList
    }

    fun toLiveDataListModel(localList : LiveData<List<CustomEntity>>) : LiveData<MutableList<CustomModel>> {
        return Transformations.map<
                List<CustomEntity>, //localList Data Type
                MutableList<CustomModel> //toListModel List Data Type
                >(
            localList) { listEntity ->
            toListModel(listEntity)
        }
    }

    fun toEntity(customModel: CustomModel) : CustomEntity {
        return when(customModel.id) {
            null -> {
                CustomEntity(
                    customModel.title?:"",
                    customModel.desc?:"",
                    customModel.date?:""
                )
            }
            else -> {
                CustomEntity(
                    customModel.id!!,
                    customModel.title?:"",
                    customModel.desc?:"",
                    customModel.date?:""


                )
            }
        }
    }
}