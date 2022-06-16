enum class Direction2(private val coordinate: Coordinate){ //枚举有属性
    EAST(Coordinate(1,0)),
    WEST(Coordinate(-1,0)),
    SOUTH(Coordinate(-1,0)),
    NORTH(Coordinate(1,0));

    //枚举类 定义 函数
    fun updateCoordinate(playerCoordinate: Coordinate) =
        Coordinate(playerCoordinate.x + coordinate.x,
            playerCoordinate.y + coordinate.y)

}

fun main() {
    println(Direction2.EAST.updateCoordinate(Coordinate(10,20)))
}