fun main() {
    //给到文案
    val getDiscountWords = {goodsName:String, hour:Int ->
        val currentYear = 2027
        "${currentYear}年，双11${goodsName}促销倒计时：$hour 小时"
    }

    showOnBoard("卫生纸",getDiscountWords)
}

//具名函数 showOnBoard为函数名,getDiscountWords 函数类型
//负责显示
private inline fun showOnBoard(goodsName:String, getDiscountWords: (String,Int) -> String){
    //shuffled乱序 提供时间
    val hour = (1..24).shuffled().last()
    println(getDiscountWords(goodsName,hour))
}

const val MAX1 = 500
