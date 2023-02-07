fun main() {

    val result = listOf("Jack","Jimmy","Rose","Tom")
        .filter { it.contains("J") }

    println(result)


    val items = listOf(
        listOf("red apple", "green apple", "blue apple"),
        listOf("red fish", "blue fish"),
        listOf("yellow banana", "teal banana")
    )

    val redItems = items.flatMap { it.filter { it.contains("red") } }
    println(redItems)

}