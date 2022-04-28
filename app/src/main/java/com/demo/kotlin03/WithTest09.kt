fun main() {
    val result1 = "The people's Republic of China.".run {
        length >= 10
    }
    //with 使用很少
    val result2 = with("The people's Republic of China.") {
        length >= 10
    }
    println(result1)
    println(result2)
}