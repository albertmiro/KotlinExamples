package com.albertmiro.myplayer.examples

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.albertmiro.myplayer.MediaItem
import java.io.File

/*
    //Notas generales sobre kotlin

    val -> variable final, inmutable
    var -> mutable

    //No hay conversion automatica de tipos, pero se pueden usar los métodos que ya dispome
    //para convertirlos

    //Para hacer casting de View a TextView se utiliza el "as"
    val textView = findViewById(R.id.textView) as TextView;

    //Properties, no se utilizan los get / set
    textView.text = "Hello Kotlin!"
    val text = textView.text

    //Se pueden poner variables dentro de los Strings facilmente
    val language = "Kotlin"
    toast("Hello $language")

    Las clases por defecto son finales, cerradas, si no se pone "open class <CLASS_NAME>"

*/

class Developer(var name: String, val age: Int = 20)

class Developer2(name: String, age: Int) {

    //    var name : String = "Name: $name"
    var name: String = name
        set(value) {
            //field seria el campo que esta property esta utilizando, cuando se hace el set
            //podemos hacer acciones sobre este valor
            if (value.isNotEmpty()) field = value
        }
        get() = "Name: $field "

    var age: Int = age

    init {

    }

    constructor(name: String) : this(name, 20)
}

fun playWithDev() {
    val dev = Developer("Pepe", 30)
    dev.name = "Paco"
    val name = dev.name

    val dev2 = Developer("Paco")
    val dev3 = Developer(age = 25, name = "Paco")
}


//Función que devuelve el valor 3 directamente, no hace especificar el tipo, se puede hacer asi
fun getInt() = 3

//Ejemplo con listas
fun testList() {
    val list = emptyList<MediaItem>()
    list.forEach { print(it) }
    list.forEach(::print)

    val mediaItem = MediaItem(1, "", "", MediaItem.Type.PHOTO)
    when (mediaItem) {
        in list -> "yes"
    }

}

//Ejemplos con lambdas
fun testLambda(list: List<Int>) {
    //Ejemplo de definción
    val f1: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

    val sum = { x: Int, y: Int -> x + y }
    val mul = { x: Int, y: Int -> x * y }
    applyF(sum, 3, 4)
    applyF(mul, 3, 4)
}

fun <T, U> applyF(f: (T, T) -> U, i1: T, i2: T) {
    f(i1, i2)
}

fun applyF(f: (Int, Int) -> Int, i1: Int, i2: Int) {
    //Se puede hacer el invoke o llamar directamente a la función
//    f.invoke(i1,i2)
    f(i1, i2)
}

//Ejemplo de composición de funciones
//fun compose(f1 : (Int) -> Float, f2: (Float) -> String) : (Int) -> String {
//    return f2(f1())
//}

//Ejemplos de for
fun textFor(viewGroup: ViewGroup) {
    val list: List<Int> = listOf(1, 2)
    for (int in list) {
    }

    //El until the hace hasta childCount-1
    for (i in 0 until viewGroup.childCount) {
    }

    for (i in 0..viewGroup.childCount - 1) {
    }
}

//Ejemplos con when
fun testWhen(view: View) {
    when (view) {
        is TextView -> view.text
        is ViewGroup -> {
            view.getChildAt(3)
        }
        is Button -> view.setOnClickListener { "Hello" }
        else -> throw Exception("Not valid")
    }

    val x = 20
    val y = "Hello"
    val z = TextView(view.context)

    when {
    //Hace la primera que se cumpla, tipo if then else
        x > 20 -> print("x > 20")
        y.contains("H") -> print("")
        z is TextView -> z.text = "Hello"
    }

    val isBigValue = if (x > 20) true else false

    val file = try {
        File("X")
    } catch (e: Exception) {
        File("Y")
    }
}