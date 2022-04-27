//string模板
fun main() {
    val origin = "Jack"
    val dest = "Rose"
    println("$origin love $dest")

    val flag = false
    println("Answer is: ${if(flag) "我可以" else "对比起"}")
}