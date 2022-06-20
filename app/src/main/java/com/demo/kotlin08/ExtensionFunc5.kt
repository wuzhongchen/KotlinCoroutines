infix fun String.printWithDefault(default: String) = print(this ?: default)
infix fun String?.printWithDefault2(default: String) = print(this ?: default)
//infix适用于有单个参数的扩展和类函数
//调用时 接受者和函数之间的 点 操作以及参数的一对括号都可以不用带
fun main() {
    val nullableString: String? = null
    println("1:")
    nullableString?.printWithDefault("abc")
    println("\n2:")
    nullableString.printWithDefault2("abc")

    nullableString printWithDefault2 "abc"

    "jack".to(18)
    mapOf("jack" to 18)
}
