
fun main() {
    println(doSomething(5, false))

    fix("Jack") //因为有默认值2

    //具名函数参数 如果使用命名值参 就可以不用管值参的顺序
    println(fix(age=10,name="Rose"))

//    TODO函数的任务 就是抛出异常
    TODO("nothing")
    println("after nothing.")
}

//可见性修饰符 fun函数声明关键字 doSomething函数名, 函数参数, String返回类型
//如果不打算传入值参 可以预先给参数指定默认值
private fun doSomething(age:Int = 2, flag:Boolean):String{
    return "result"
}

fun fix(name:String, age:Int = 2){
    println(name + age)
}