package com.demo.kotlin04_

class Player0{

    //针对你定义的每一个属性 Kotlin都会产生一个field 一个getter以及一个setter
    var name:String = "ja ck"
        get() = field.capitalize()
        set(value){
            field = value.trim()
        }

    val realValue
        get() = (1..24).shuffled().last()

    /**
     * 防范 - 竞态 - 条件
     * 如果一个属性 既可空 也可变
     * 引用之前 必须保证它非空
     * 用also函数
     */
    var words:String? = "my friends"
    fun saySth() {
        words?.also {
            println("Hello, ${it.uppercase()}")
        }
    }
}

fun main() {
    var p = Player0()
//    println(p.name)
    p.name = " rose "
    println(p.name)
    println(p.realValue)
    p.saySth()
}
