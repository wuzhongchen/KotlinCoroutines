fun main() {
    val str1 = "Jason"
    val str2 = "jason".capitalize()
    println(str1 == str2)
    println(str1 === str2)  //true 1 false 2

    "The people's Republic of China.".forEach {
        print("$it* ")
    }

}