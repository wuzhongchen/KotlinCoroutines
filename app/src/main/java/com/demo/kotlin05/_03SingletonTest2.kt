open class Player{
    open fun load() = "loading nothing.."
}

fun main() {
    //相当于 new View.ClickListener
    val p = object : Player(){
        override fun load() = "anonymous nothing.."
    }

    println(p.load())
}