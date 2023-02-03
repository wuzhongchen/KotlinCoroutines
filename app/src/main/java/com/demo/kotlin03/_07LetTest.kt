fun main() {
    val result = listOf(3, 2, 1).first().let {
        it * it
    }
    println(result)

    val firstElement = listOf(3, 2, 1).first()
    val result2 = firstElement * firstElement

    println(formatGreeting("Jack"))
    println(formatGreeting2("Jack"))

}

fun formatGreeting(guestName: String?): String {
    //?. 安全调用
    return guestName?.let {
        "Welcome, $it."
    } ?: "What's your name?"
}

fun formatGreeting2(guestName: String?): String {
    return if(guestName != null){
        "Welcome, $guestName."
    }else{
        "What's your name?"
    }
}