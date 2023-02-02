 import java.io.File
 //《87-继承与重载的open关键字》
 //类默认都是封闭的，要让某个类开放继承，必须使用open关键字修饰
open class Product(val name: String) {
    fun description() = "Product: $name"

    open fun load() = "Nothing.."
}

class LuxuryProduct : Product("Luxury") {

    override fun load() = "LuxuryProduct loading..."

    fun special() = "LuxuryProduct special function"

    fun specialX() = "LuxuryProduct special x function"

    fun special2() = "LuxuryProduct special 2 function"
}

fun main() {
//《88-类型转换》
    val p: Product = LuxuryProduct()
    println(p.load())

    //kotlin is 运算符 用来检查某个对象的类型
    println(p is Product)
    println(p is LuxuryProduct)
    println(p is File)

    if(p is LuxuryProduct){
        println((p as LuxuryProduct).special())
    }

    val x = (p as LuxuryProduct)

    //转成 子类类型
    println((p as LuxuryProduct).special())
    println(x.specialX())
    //只要转换一次 as, Kotlin编译器就会智能判断 后续不用转换了
    println(p.special2())

    //《90-Any超类.mp4》
    //java里的所有类的超类是object kotlin所有类的超类是Any
    println(p is Any)
    println(p.toString())
}