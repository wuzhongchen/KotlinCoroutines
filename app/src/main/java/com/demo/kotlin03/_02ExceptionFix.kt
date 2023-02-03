//异常处理
fun main() {
    var number: Int? = null

    try {
        number!!.plus(1)
    }catch (e: Exception){
        println(e)
    }

    try {
        checkOperation(number)
        number!!.plus(1)
    }catch (e: Exception){
        println(e)
    }
}

fun checkOperation(number: Int?){
    //number ?: throw UnskilledException()
    /**
     *     先决条件函数 checkNotNull requireNotNull Error Assert
     */
    checkNotNull(number, {"自己做一个检查，抛出自定义异常。"})
}

//fun checkOperation(number: Int?){
//    number ?: throw UnskilledException()
//}

fun checkOperation2(number: Int?){
    //number ?: throw UnskilledException()
    checkNotNull(number) { "自己做一个检查，抛出自定义异常。" }
}

//自定义异常
class UnskilledException() : IllegalArgumentException("操作不当")