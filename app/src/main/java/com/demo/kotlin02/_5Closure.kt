import java.util.*

fun main() {
    //打乱数组。
    val a = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17)
    val r = Random()
    for (i in a) {
        val x = r.nextInt(a.size)
        val temp = a[i]
        a[i] = a[x]
        a[x] = temp
    }
    for (i in a) {
        print(a[i].toString() + ",")
    }
    val getDiscountWords1 = getDiscountWords1()
    val getDiscountWords2 = configDiscountWords2()
    println(getDiscountWords1(true))
    println(getDiscountWords2("牙膏"))
}

//String{ 为函数返回类型 (Boolean)为传入类型
fun getDiscountWords1(): (Boolean) -> String{
    val currentYear = 2027
    val hour = (1..24).shuffled().last()
    // goodsName 引用了 configDiscountWords的 变量
    return {productOr: Boolean ->
        "${currentYear}年，双11${if(productOr) "卫生纸" else "牙膏"}促销倒计时：$hour 小时"
    }
}

//String{ 为函数返回类型
fun configDiscountWords2(): (String) -> String{
    val currentYear = 2027
    val hour = (1..24).shuffled().last()
    // goodsName 引用了 configDiscountWords的 变量
    return {goodsName: String ->
        "${currentYear}年，双11${goodsName}促销倒计时：$hour 小时"
    }
}
