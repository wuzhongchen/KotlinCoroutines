class Player7(

){

    val name:String
    private fun firstLetter() = name[0]

    init {
        println(firstLetter())
        name = "Jack"

//        name = "Jack"
//        println(firstLetter())
    }

}

fun main() {
    Player7()
}