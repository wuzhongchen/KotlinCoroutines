//枚举
enum class _10Direction{
    EAST,
    WEST,
    SOUTH,
    NORTH
}

fun main() {
    println(_10Direction.EAST)
    println(_10Direction.EAST is _10Direction)
}