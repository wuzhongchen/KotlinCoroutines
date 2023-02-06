package com.demo.kotlin08

fun String.addExt3(amount: Int = 1) = this + "!".repeat(amount)

//泛型扩展函数
fun <T> T.easyPrint3(): T {
    println(this)
    return this
}

fun main() {
    "abc".easyPrint3().addExt3(2).easyPrint()

    val i = "abc".let {
        50
    }
}
