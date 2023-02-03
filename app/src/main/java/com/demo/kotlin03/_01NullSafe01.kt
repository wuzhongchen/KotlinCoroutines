import java.util.*

fun main() {
//    为了避免nullpoint 不允许给非空类型赋null
//    var str1 = "butterfly"
//    str1 = null

    //可空字符串类型
    var str2:String? = "butterfly"
    str2 = null

    var str3:String? = "theryOrey"
//    str3 = null
    //?. 安全调用操作符
    println(str3?.capitalize())
    println(str3?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })

    var str:String? = null
//    str = ""
    //println(str?.capitalize())
    /**
     * let函数 返回lambda最后一行执行结果
     */
    str = str?.let {
        //非空白的字符串
        if(it.isNotBlank()){
            it.capitalize()
        }else{
            "lambda_last"
        }
    }
    println(str)

    //非空断言操作符 抛异常
    str = "null"
    println(str!!.capitalize())


     //==============================

    var str4:String? = "lambda_last"
    if(str4 != null){
        str4 = str4.capitalize()
    }else{
        println("为null.")
    }

    //?. 安全调用操作符 支持函数链式调用
    str4 = str4?.capitalize()?.plus(" is great.")
    println(str4)

    //?:空合并操作符 如果左边的值为null 就使用右边的结果值
    str4 = null
    println(str4 ?: "jack")

    str4 = null
    str4 = str4?.let{
        it.capitalize()
    } ?: "right args"
    println(str4)
}