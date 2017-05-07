package com.albertmiro.myplayer

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.albertmiro.myplayer.extensions.loadUrl
import com.albertmiro.myplayer.extensions.visible
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlin.properties.Delegates
import com.albertmiro.myplayer.extensions.inflate as inflate2

private typealias Listener = (MediaItem) -> Unit

class MediaAdapter(val listener: Listener)
    : RecyclerView.Adapter <MediaAdapter.ViewHolder>() {

    //var items: List<MediaItem> by Delegates.observable(emptyList()) {p, old, new ->
    var items: List<MediaItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate2(R.layout.view_media_item)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { listener(items[position]) }

    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Diferentes formas de recuperar la vista:

//        val title = find<TextView>(R.id.media_title)
//        val thumb = find<ImageView>(R.id.media_thumb)
//        val mediaIndicator = find<ImageView>(R.id.media_video_indicator)

//        val title =itemView.media_title
//        val thumb =itemView.media_thumb
//        val mediaIndicator =itemView.media_video_indicator

        fun bind(item: MediaItem) {

            //En el with va a devolver el valor que se genere en última linea
            //(podemos asignarlo a una variable para recuperar este valor) en su defecto
            //va a devolver Unit (void)
            with(itemView) {
                media_title.text = item.title
                media_thumb.media_thumb.loadUrl(item.url)
                media_video_indicator.visible = (item.type == MediaItem.Type.VIDEO)
            }

            //El apply se devuelve a su mismo, patron builder
            val textView = TextView(itemView.context).apply {
                text = "Hello"
                textSize = 20f
                setHintTextColor(Color.BLACK)
            }

            //Si el textview no es nulo, entra en text sino no hace nada si al final el text
            //no es nulo, muestra el toast (contenido dentro del let)
//            textView?.text?.let {
//                toast(it.toString())
//            }

            //Otra forma para poner la visibility del mediaIndicator a VISIBLE o GONE
//            mediaIndicator.visibility = when(item.type) {
//                MediaItem.Type.PHOTO -> View.GONE
//                MediaItem.Type.VIDEO -> View.VISIBLE
//                else -> View.VISIBLE
//            }
        }
    }
}