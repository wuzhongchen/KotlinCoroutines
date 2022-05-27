class Player2 {

    class Equipment(var name: String) {
        fun show() = println("equipment:$name")
    }

    fun battle(){

    }

}

fun main() {
    //场景：如果一个类只对另一个类有用，那么将其嵌入到该类中 并使这两个类保持在一起
    Player2.Equipment("sharp knife").show()
}