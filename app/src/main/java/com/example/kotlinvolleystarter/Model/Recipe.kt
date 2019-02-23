package com.example.kotlinvolleystarter.Model

class Recipe() {
    var title: String? = null
    var link: String? = null
    var thumbnail: String? = null

    constructor(title: String, link: String, thumbnail: String): this() {
        this.title = title
        this.link = link
        this.thumbnail = thumbnail

    }
}