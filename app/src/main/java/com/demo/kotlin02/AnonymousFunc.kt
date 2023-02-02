fun main() {
//    匿名函数
    val total = "Mississippi".count()

    val totalS1 = "Mississippi".count({ letter ->
        letter == 's'
    })

    //如果一个函数的lambda参数排最后，或者是唯一的 参数，那么扩住lambda值参的一对圆括号可以省略
    val totalS2 = "Mississippi".count{ letter ->
        letter == 's'
    }

    //定义只有一个参数的匿名函数，可以使用it关键字表示参数名。当需要两个参数时,it则不能使用
    val totalS3 = "Mississippi".count{it == 's'}

    println(total)
    println(totalS1)
    println(totalS2)
    println(totalS3)

    //变量的类型是一个匿名函数
    //函数的类型 由传入的参数 和 返回值类型决定
    /**
     * 匿名函数 会隐式返回或自动返回函数体最后一行语句的结果
     */
    val blessingFunctio0:()->String
    blessingFunctio0 = {
        val holiday = "New Year."
        "Happy $holiday"
    }

    val blessingFunction0:()->String = {
        val holiday = "New Year."
        "Happy $holiday"
    }

//    参数name的类型放在匿名函数的类型定义括号内 参数名name则放在函数定义中
    val blessingFunction1:(Int) -> String = { year ->
        val holiday = "New Year."
        "$year, Happy $holiday"
    }

    println(blessingFunction1(2023))

    //用it
    val blessingFunction2:(String) -> String = {
        val holiday = "New Year."
        "$it, Happy $holiday"
    }

    println(blessingFunction2("Jack"))

    val blessingFunction3 = {
        val holiday = "New Year."
        "Happy $holiday"
    }

    println(blessingFunction3())

    val blessingFunction4:(String,Int) -> String = {name, year ->
        val holiday = "New Year."
        "$name, Happy $holiday $year"
    }

    println(blessingFunction4("Jack",2027))


    val blessingFunction = {name:String, year:Int ->
        val holiday = "New Year."
        "$name, Happy $holiday $year"
    }

    println(blessingFunction("Jack",2027))

}