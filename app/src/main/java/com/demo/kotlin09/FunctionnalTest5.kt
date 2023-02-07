fun main() {
    val employees = listOf("Jack", "Jason", "Tommy")
    val shirtSize = listOf("large", "x-large", "medium")
    val employeeShirtSizes = employees.zip(shirtSize).toMap()
    println(employeeShirtSizes["Jack"])

    //将每个元素值乘以3后累加起来。
    //folder
    val foldedValue = listOf(1, 2, 3, 4).fold(2) { accmulator, number ->
        println("Accmulator value:$accmulator")
        accmulator + (number * 3)
    }
    println("Final value:$foldedValue")


    val list = employeeShirtSizes.map { "${it.key},shirt size:${it.value}" }
    println(list)

}