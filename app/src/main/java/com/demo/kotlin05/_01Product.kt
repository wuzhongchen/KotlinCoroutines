 import java.io.File
 //类默认都是封闭的，要让某个类开放继承，必须使用open关键字修饰
open class Product(val name: String) {
    fun description() = "Product: $name"

    open fun load() = "Nothing.."
}

class LuxuryProduct : Product("Luxury") {

    override fun load() = "LuxuryProduct loading..."

    fun special() = "LuxuryProduct special function"
}

fun main() {
    val p: Product = LuxuryProduct()
    println(p.load())

    //kotlin is 运算符 用来检查某个对象的类型
    println(p is Product)
    println(p is LuxuryProduct)
    println(p is File)

    /*if(p is LuxuryProduct){
        println((p as LuxuryProduct).special())
    }*/

    val x = (p as LuxuryProduct)

    //转成 子类类型
    println((p as LuxuryProduct).special())
    println(p.special())


    println(p is Any)
    println(p.toString())
}