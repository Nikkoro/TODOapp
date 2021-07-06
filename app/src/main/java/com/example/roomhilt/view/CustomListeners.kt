package com.example.roomhilt.view

import com.example.roomhilt.model.CustomModel

interface CustomListeners {


    fun onUpdate(item : CustomModel, position : Int)

    fun onDelete(item : CustomModel, position : Int)
}