package com.albertmiro.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import co.metalab.asyncawait.async
import com.albertmiro.myplayer.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() , Logger {
//Estamos extendiendo de Logger, para hacer un override del tag:
//    override val tag = MainActivity::javaClass.name

    private val adapter = MediaAdapter { navigateTo(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter

        updateData(Filter.None)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var filter = when (item.itemId) {
            R.id.filter_photos -> Filter.ByType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(MediaItem.Type.VIDEO)
            else -> Filter.None
        }

        updateData(filter)

        return true
    }

    fun updateData(filter : Filter) {

        //Uso de corutinas, va a ejecutar este codigo secuencialmente, esperando en el await
        async {
            val cats = await { MediaProvider.fetchMedia("animals") }
            val nature = await { MediaProvider.fetchMedia("nature") }
            val items = cats + nature

            adapter.items = when(filter) {
                Filter.None -> items
                is Filter.ByType -> items.filter { it.type == filter.type }
            }
        }
    }

    private fun navigateTo(item: MediaItem) {
        toast(item.title)
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to item.id)
    }
}

interface Logger {
    //Las interfaces en Kotlin pueden contener codigo

    val tag: String get() = javaClass.name

    fun d(message: String) {
        Log.d(tag, message)
    }

    fun e(message: String) {
        Log.e(tag, message)
    }
}
