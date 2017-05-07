package com.albertmiro.myplayer

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

private val BASE_URL = "http://lorempixel.com/400/400"

//Los objects serian equivalentes a las clases Singleton
object MediaProvider {

    fun fetchMedia(category: String): List<MediaItem> = (1..10).map {
        MediaItem(it,
                "Title $it",
                "$BASE_URL/$category/$it",
                if (it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO)
    }

    //Ejemplo de fetchMedia con bloque async y uiThread
    fun fetchMediaAsync(callback: (List<MediaItem>) -> Unit) {
        doAsync {
            val items = fetchMedia("cats")
            uiThread {
                callback(items)
            }
        }
    }
}

//Ejemplos de como usar los Pair y operaciones infix
fun testWithPairs() {
    val pair = Pair("X", 20)
    val pair2 = "X" to 20
    val res = 20 sum 30
}

//Definición de un infix
infix fun Int.sum(other: Int): Int = this + other

fun fetchMediaOldWay(): List<MediaItem> = listOf(
        MediaItem(1, "Title 1", BASE_URL + 1, MediaItem.Type.PHOTO),
        MediaItem(2, "Title 2", BASE_URL + 2, MediaItem.Type.PHOTO),
        MediaItem(3, "Title 3", BASE_URL + 3, MediaItem.Type.VIDEO),
        MediaItem(4, "Title 4", BASE_URL + 4, MediaItem.Type.PHOTO),
        MediaItem(5, "Title 5", BASE_URL + 5, MediaItem.Type.PHOTO),
        MediaItem(6, "Title 6", BASE_URL + 6, MediaItem.Type.VIDEO),
        MediaItem(7, "Title 7", BASE_URL + 7, MediaItem.Type.PHOTO),
        MediaItem(8, "Title 8", BASE_URL + 8, MediaItem.Type.PHOTO)
)

