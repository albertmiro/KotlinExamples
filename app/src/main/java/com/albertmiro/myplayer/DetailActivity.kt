package com.albertmiro.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.albertmiro.myplayer.extensions.loadUrl
import com.albertmiro.myplayer.extensions.visible
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    //Un companion object es el equivalente a una variable estatica de Java,
    //es un objecto unico que van a compartir todas las instancias de esta clase
    companion object {
        val EXTRA_ID = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(EXTRA_ID, -1)

        MediaProvider.fetchMediaAsync { items ->
            val item = items.firstOrNull { it.id == id }
            item?.let {
                detail_thumb.loadUrl(item.url)
                detail_video_indicator.visible = item.type == MediaItem.Type.VIDEO
                supportActionBar?.title = item.title
            }
        }
    }
}
