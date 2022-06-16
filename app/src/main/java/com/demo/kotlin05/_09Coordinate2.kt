data class _09Coordinate2(var x: Int, var y: Int) {
    val isInBounds = x > 0 && y > 0

    operator fun plus(other: _09Coordinate2) = _09Coordinate2(x + other.x, y + other.y)
}

fun main() {

    val c1 = _09Coordinate2(10, 20)
    val c2 = _09Coordinate2(10, 20)

    println(c1 + c2)
}

//  操作符   函数名
//
//  +       plus
//
//  +=      plusAssign
//
//  ==      equals
//
//  >       compareTo
//
//  []      get
//
//  ..      rangeTo
//
//  in      contains