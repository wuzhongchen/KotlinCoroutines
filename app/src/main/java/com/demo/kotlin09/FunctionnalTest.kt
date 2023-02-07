fun main() {
    val animals = listOf("zebra", "giraffe", "elephant", "rat")
    val babies = animals
        .map { animal -> "A baby $animal"}
//        .map { baby -> "$baby,with the cutest little tail ever!" }
    println(animals)
    println(babies)

    val animalsLength = animals.map { it.length }
    println(animalsLength)

    //List<String>  List<List<String>>
}