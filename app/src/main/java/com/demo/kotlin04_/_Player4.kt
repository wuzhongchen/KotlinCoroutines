class Player4 {
    //延迟初始化
    lateinit var equipment:String

    fun ready(){
        equipment = "sharp knife"
    }

    fun battle(){
        //只要无法确认变量是否初始化 可以执行
        if (::equipment.isInitialized) println(equipment)
    }
}

fun main() {
    val p = Player4()
    p.ready()
    p.battle()
}