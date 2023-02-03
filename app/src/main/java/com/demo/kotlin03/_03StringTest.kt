//字符串操作
const val NAME = "Jimmy's friend"
const val NAMES = "jack,cidy,jason,loluy"
fun main() {

    val index = NAME.indexOf('\'')
    val str = NAME.substring(0, index)
    println(str)
    //int range
    var str2 = NAME.substring(0 until index)
    println(str2)

    //split返回的是List集合
    val data = NAMES.split(",")
    data[0]

    val (origin,dest,proxy,gullo) = NAMES.split(",")
    println("$origin/ $dest/ $proxy/ $gullo\n")

    val str1 = "The people's Republic of China."
    println("***明文***:\n$str1\n")

    var plusStr = ""
    val str3 = str1.forEach {
            thisChar ->
        val dStr = when(thisChar){
            'a' -> "8"
            'e' -> "6"
            'i' -> "9"
            'o' -> "1"
            'u' -> "3"
            else -> thisChar
        }
        plusStr += dStr
    }
    println("加密之后：\n$plusStr")

    val str4 = str1.replace(Regex("[aeiou]")){
        when(it.value){
            "a" -> "8"
            "e" -> "6"
            "i" -> "9"
            "o" -> "1"
            "u" -> "3"
            else -> it.value
        }
    }


    println("加密之后：\n$str4")

    

}