import java.io.File

fun main() {
    /**
     * 读取见MainActivity
     **/
    var file = File("/Users/hs/AndroidStudioProjects/Kt_Demo/txt/i have a dream.txt")
    val result = file.run {
        readText().contains("great")
    }
    println(result)

    val result2 = "The people's Republic of China.".run(::isLong)
    println(result2)

    "The people's Republic of China."
        .run (::isLong)
        .run (::showMessage)
        .run (::println)
}

fun isLong(name: String) = name.length >= 10

fun showMessage(isLong:Boolean):String{
    return if(isLong){
        "名字太长了."
    }else{
        "Please rename."
    }
}