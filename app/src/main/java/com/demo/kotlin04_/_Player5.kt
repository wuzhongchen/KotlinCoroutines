class Player5(_name:String) {
    var name = _name

    //可以暂时不初始化变量 直到首次使用它 惰性初始化
    val config by lazy {loadConfig()}

//    val config = loadConfig()
    private fun loadConfig():String{
        println("loading...")
        return "xxx"
    }
}

fun main() {
    val p = Player5("Jack")
    Thread.sleep(3000)
    println(p.config)
}