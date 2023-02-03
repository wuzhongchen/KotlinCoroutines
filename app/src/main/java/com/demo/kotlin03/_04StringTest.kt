fun main() {
    val str1 = "Jason"
    val str2 = "jason".capitalize()
    println(str1 == str2)
    //比较的是两个对象的地址是否相等
    println(str1 === str2)  //true 1 false 2

    "The people's Republic of China.".forEach {
        print("$it* ")
    }

}