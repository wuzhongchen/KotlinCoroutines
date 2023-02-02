fun main() {
    //函数引用
    //::使用 Kotlin中双冒号操作符,表示把一个函数当做一个参数,传递到另一个方法中进行使用,通俗的来讲就是引用一个函数
    val hour = (1..24).shuffled().last()
    showOnBoard("牙膏",hour, ::getDiscountWords)
    println(showBoard("牙膏", hour));
}

//具名函数
private fun getDiscountWords(goodsName: String, hour: Int):String{
    val currentYear = 2027
    return "${currentYear}年，双11${goodsName}促销倒计时：$hour 小时"
}

private fun showOnBoard(goodsName:String, hour:Int, getDiscountWords: (String,Int) -> String){
    println(getDiscountWords(goodsName,hour))
}

val showBoard: (String,Int) -> String = ::getDiscountWords
val block: (Int, Int) -> Int = ::sum

fun sum(firstNumber: Int, secondNumber: Int): Int {
    return firstNumber + secondNumber
}

//作用域管理
const val MAX = 500