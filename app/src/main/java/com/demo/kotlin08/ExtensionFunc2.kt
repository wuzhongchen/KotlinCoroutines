package com.demo.kotlin08
fun String.addExt2(amount: Int = 1) = this + "!".repeat(amount)

fun Any.easyPrint2(): Any {
    println(this)
    return this
}

fun main() {
    // easyPrint Any类型 无法转 String类型
//    "abc".easyPrint2().addExt2(2).easyPrint()
}
