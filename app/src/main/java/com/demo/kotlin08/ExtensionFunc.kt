package com.demo.kotlin08
//定义扩展函数
//给字符串追加若干个问号
val StringaddExt:(Int, String) -> String = { amount, s->
    s + "?".repeat(amount)
}
//给字符串追加若干个感叹号

fun String.addExt(amount: Int = 1) = this + "!".repeat(amount)

fun Any.easyPrint() = println(this)

fun main() {
    println(StringaddExt(2, "abc追加"))
    println("abc追加".addExt(2))
    "abc".easyPrint()
    15.easyPrint()
}
