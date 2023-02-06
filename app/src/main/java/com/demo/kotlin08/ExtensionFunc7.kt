import java.io.File

//扩展函数
fun String.addExt() = "!".repeat(count())

//泛型的扩展函数 没有返回值类型
fun <T> T.easyPrint(): Unit = println(this)

//为什么要传入扩展函数（泛型），而不是一个普通的匿名函数？
//T.() -> Unit
//扩展函数里自带了接收者对象的this隐式调用
//为什么是泛型的扩展函数？

//匿名函数，也可以是扩展函数
//普通的匿名函数
//() -> Unit
//匿名函数内部this指向一个File对象，隐式调用
//File.() -> Unit

public inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

public inline fun File.apply2(block: File.() -> Unit): File {
    block()
    return this
}

public inline fun <T> T.apply3(block: () -> Unit): T {
    block()
    return this
}

fun main() {
    val file1 = File("xx").apply {
        setReadable(true)
    }
    val file2 = File("xx").apply2 {
        setReadable(true)
    }

    //这里分解一下
    //1.定义扩展函数
    fun File.ext(): Unit {
        setReadable(true)
    }
    //2.给block变量赋值
    val block = File::ext
    //3.传入apply函数
    File("xx").apply3 { block }

    //DSL 使用这样的编程范式
    //可以写出 领域特定语言(DSL)
    //一种API编程范式 暴露接受者的函数 和 特性
    //以便于使用你定义的lambda表达式来读取和配置他们
}
