//data 关键字
class Coordinate(var x: Int, var y: Int) {
    val isInBounds = x > 0 && y > 0
}

data class Coordinate2(var x: Int, var y: Int) {
    val isInBounds = x > 0 && y > 0
}

fun main() {
    println(Coordinate(10, 20))
    // == 比较的是内容，equals，
    // 没有重写 Any的equals方法 默认实现===，比较引用
    // === 比较的是引用
    println(Coordinate(10, 20) == Coordinate(10, 20)) //false
    println(Coordinate(10, 20) === Coordinate(10, 20)) //false

    //数据类 覆盖Any超类的equals方法 会比较属性值 数据结构
    //同时重写了hashcode 因为equals相同的情况 会比较Hashcode也要相同
    println(Coordinate2(10, 20) == Coordinate2(10, 20))
    println(Coordinate2(10, 20) === Coordinate2(10, 20))

    //解构 声明
    val (x, y) = Coordinate2(10, 20)
    println("$x, $y")
}