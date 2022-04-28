import java.io.File

fun main() {
    val result1 = File("/Users/hs/AndroidStudioProjects/Kt_Demo/txt/i have a dream.txt")
            //如果需要判断某个条件是否满足，再决定是否可以赋值
            //如果判断结果是true 则返回接受者 对象，
            //false 则返回null
            //避免临时变量赋值的麻烦
        .takeIf { it.exists() && it.canRead() }
        ?.readText()

    println(result1)

    println("==================================")
    println("**********************************")
    println("==================================")
    println("**********************************")
    println("==================================")
    println("**********************************")
    println("==================================")
    println("**********************************")
    println("==================================")
    println("**********************************")
    println("==================================")
    println("**********************************")

    val result2 = File("/Users/hs/AndroidStudioProjects/Kt_Demo/txt/i have a dream.txt")
        //如果判断结果是false 则返回接受者 对象，
        //true 则返回null
        .takeUnless { it.isHidden }
        ?.readText()

    println(result2)
}