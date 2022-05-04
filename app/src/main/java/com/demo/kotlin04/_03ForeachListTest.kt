fun main() {
    val list = listOf("Jason", "Jack", "Jacky")
    for (s in list) {
        println(s)
    }

    list.forEach {
        println(it)
    }

    list.forEachIndexed { index, item ->
        println("$index, $item")
    }


    //下划线 不赋值
    val (origin,_,proxy)  = list
}