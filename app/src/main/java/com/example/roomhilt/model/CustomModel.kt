package com.example.roomhilt.model

class CustomModel {

    var id: Int? = null
    var title: String? = null
    var desc: String? = null
    var date: String? = null


    constructor(title : String,desc: String,date: String) {
        this.title = title
        this.desc = desc
        this.date = date
    }



    /*constructor(id : Int, title : String, desc : String, date : String) {
        this.title = title
        this.desc = desc
        this.date = date
    }*/

    constructor(id : Int, title : String, desc : String, date : String) {
        this.id = id
        this.title = title
        this.desc = desc
        this.date = date
    }
}