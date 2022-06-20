package com.demo.kotlin08

val String.numVowels
    get() = count { "aeiou".contains(it) }


fun <T> T.easyPrint(): T {
    println(this)
    return this
}

fun main() {
    //统计中华人民共和国 有多少个元音字母
    "The people's Republic of China".numVowels.easyPrint()
}
