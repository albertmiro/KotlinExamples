package com.albertmiro.myplayer

data class MediaItem(val id: Int, val title: String, val url: String, val type: Type) {

    //En Kotlin podemos añadir un id, por ejemplo para identificar los elementos de dentro
    //de un enum:
    //enum class TypeWithId(val id:Int) {PHOTO(4), VIDEO(7)}

    enum class Type {PHOTO, VIDEO }
}