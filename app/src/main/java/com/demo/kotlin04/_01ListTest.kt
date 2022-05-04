fun main() {
    val list = listOf("Jason", "Jack", "Jacky")
    //println(list[3])
    println(list.getOrElse(3){"Unknown"})
    println(list.getOrNull(2) ?: "Unknown")
}