package com.demo.kotlin

const val MAX = 200
fun main() {
    println("Hello Kotlin!")

    var a: Int = 10
    a *= 10
    println("a=" +a)
    println("a=$a")
    println(a)

    var str:String = "Hello Kotlin!"
    println(str)

//    Kotlin内置数据类型
//    String 字符串
//            Char 单字符
//            Boolean true/false
//    Int
//    Double
//    List 1,8,10 "Jack","rose","Jack"
//    Set 不重复的元素集合 "Jack","rose","Jacky"
//    Map "small" to 5 "medium" to 8 "large" to 9

    val a2 : Int = 10
//  Error:  a2 *= 10
    println("a2=$a2")

    val str2 = "对于已声明并赋值的变量，它允许你省略类型定义。"
    println(str2)
}