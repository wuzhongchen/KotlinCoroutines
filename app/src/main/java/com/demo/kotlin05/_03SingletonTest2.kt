open class Player{
    open fun load() = "loading nothing.."
}
//《92-对象表达式》
fun main() {
    //匿名内部类
    //相当于 new View.ClickListener
    val p = object : Player(){
        override fun load() = "anonymous nothing.."
    }

    println(p.load())
}