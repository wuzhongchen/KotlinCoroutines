fun main() {
    //set 不允许重复元素
    val set = setOf("Jason", "Jack", "Jacky", "Jack")
    println(set.elementAt(2))

    val mutableSet= mutableSetOf("Jason", "Jack", "Jacky", "Jack")
    mutableSet += "Jimmy"

    //去重
    val list = listOf("Jason", "Jack", "Jacky", "Jack")
        .toSet()
        .toList()
    println(list)

    //直接用List本身方法
    println(listOf("Jason", "Jack", "Jacky", "Jack").distinct())
}