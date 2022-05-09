class Player8(
    //1.主构造函数里声明的属性
    _name:String
){

    val name:String = _name
    val playerName:String = initPlayerName()
//    val playerName:String = initPlayerName()
//    val name:String = _name
    private fun initPlayerName() = name
}

fun main() {
    println(Player8("Jack").playerName)
}