fun main() {
    /**
     *     对标JavaAnonymousClass
     */
    //省略getDiscountWords函数定义的写法
    showOnBoard("卫生纸", {goodsName:String, hour:Int ->
        val currentYear = 2027
        "${currentYear}年，双11${goodsName}促销倒计时：$hour 小时"
    })

    //如果一个函数的lambda参数排最后，或者是唯一的 参数，那么扩住lambda值参的一对圆括号可以省略
    showOnBoard("卫生纸"){goodsName:String, hour:Int ->
        val currentYear = 2027
        "${currentYear}年，双11${goodsName}促销倒计时：$hour 小时"
    }
}

//具名函数
private fun showOnBoard(goodsName:String, getDiscountWords: (String,Int) -> String){
    val hour = (1..24).shuffled().last()
    println(getDiscountWords(goodsName,hour))
}