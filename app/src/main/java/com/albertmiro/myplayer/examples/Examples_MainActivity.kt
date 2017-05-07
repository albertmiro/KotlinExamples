package com.albertmiro.myplayer.examples

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.NotificationCompat
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import com.albertmiro.myplayer.Logger
import com.albertmiro.myplayer.MainActivity
import com.albertmiro.myplayer.MediaAdapter
import com.albertmiro.myplayer.R
import com.albertmiro.myplayer.extensions.find
import com.albertmiro.myplayer.extensions.startActivity

class Examples_MainActivity : AppCompatActivity(), Logger {

    private val adapter = MediaAdapter {}

    val recycler2 by lazy {
        //Inicializacion si se necesita en el onCreate, sino no se llega a crear nunca, asi cuando
        //se llama esta vista ya esta inflada y evita que nos encontremos con nulls cuando vamos
        //a acceder a ella
        findViewById(R.id.recycler) as RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        d("This is a debug message")

        val recycler = find<RecyclerView>(R.id.recycler)
        findViewById(R.id.recycler) as RecyclerView
//        recycler.adapter = MediaAdapter(fetchMedia(), { toast(it.title) })
        recycler.adapter = adapter


        val textView = TextView(this).apply2 {
            text = "Hello"
            textSize = 20f
        }

        val notification = notify(1) {
            //Inicializar notificacion
            title = "My notification"
            mContentText = "Notification content text"
        }

        //Si solo tiene un parametro se pueden quitar los parentesis
        recycler.setOnClickListener { view -> toast("hello") }

        //Para referirnos al context de la MainActivity: this@MainActivity.toast(mediaItem.title)

        startActivity<MainActivity>()
    }


    //Función de extension que esta dentro de otra función de extension con lambdas
    //Con este ejemplo T va a ser el textview y va a ejecutar lo que esta dentro del bloque
    //del apply2 { ... } y devolvera este mismo TextView con los valores seteados
    fun <T : Any> T.apply2(f: T.() -> Unit): T {
        this.f()
        return this
    }

    //Función de extensión para crear una notificación utilizando lambdas (estilo apply2)
    fun Context.notify(id: Int, f: NotificationCompat.Builder.() -> Unit): Notification {
        val builder = NotificationCompat.Builder(this)
        builder.f()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = builder.build()
        manager.notify(id, notification)
        return notification
    }

    //Unit seria el void de Java, todas las variables son objeto y todas las funciones devuelven algo
    private fun toast(value: String): Unit {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
    }
}