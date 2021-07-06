package com.example.roomhilt.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "custom_table")
data class CustomEntity(

    @ColumnInfo(name = "Title")
    var title : String? = null,

    @ColumnInfo(name= "Desc")
    var desc : String? = null,

    @ColumnInfo(name = "Date")
    var date : String? = null

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int? = null

    constructor(id : Int, title : String, desc : String, date : String) : this() {
        this.id = id
        this.title = title
        this.desc = desc
        this.date = date
    }

    override fun toString(): String {
        return "MessageThreadListEntity(Id=$id, Title=$title, Desc=$desc, Date=$date)"
    }
}