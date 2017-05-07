package com.albertmiro.myplayer.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun Context.toast(value: String, length: Int = Toast.LENGTH_SHORT): Unit {
    Toast.makeText(this, value, length).show()
}

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadUrl(url: String) {
    Picasso.with(context).load(url).into(this)
}

//Propiedades de extensión
val ViewGroup.items: List<View>
    get() = (0 until childCount).map { getChildAt(it) }


//Inline: sustituye la llamada a este metodo por el contenido de esta función
inline fun <reified T : Activity> Context.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : ViewGroup> Activity.find(resId: Int): T = findViewById(resId) as T

inline fun <reified T : View> RecyclerView.ViewHolder.find(layoutRes: Int): T {
    return itemView.findViewById(layoutRes) as T
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }


//Operator overloading:
operator fun ViewGroup.get(position: Int): View {
    return getChildAt(position)
}

operator fun TextView.plus(other: TextView): TextView {
    text = "$text  ${other.text}"
    return this
}

//Example using operator overloading
fun test(vg: ViewGroup, v1: TextView, v2: TextView) {
    val view = vg[0]
    val v3: TextView = v1 + v2
}
