import kotlin.math.roundToInt

fun main() {

    //数字 类型转换 安全操作
//    val number1: Int = "8.98".toInt()
    val number2: Int? = "8.98".toIntOrNull()
//    println(number1)
    println(number2)

    //精度损失
    println(8.956756.toInt())
    //四舍五入
    println(8.956756.roundToInt())

    val s = "%.2f".format(8.956756)
    println(s)
}