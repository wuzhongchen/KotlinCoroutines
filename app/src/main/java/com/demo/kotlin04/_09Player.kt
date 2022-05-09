import kotlin.math.absoluteValue

class Player(
    //临时变量（包括仅引用一次的参数 通常都会以下划线开头的名字命名）
    _name: String,
    _age: Int,
    _isNormal: Boolean
) {

    var name = _name
        get() = field.capitalize()
        private set(value){ //设置只读 不可改变
            field = value.trim()
        }

    var age = _age
        get() = age.absoluteValue //返回绝对值
        set(value) {
            field = age.absoluteValue
        }

    var isNormal = _isNormal

}

fun main() {
    val p = Player("Jack", 20, true)
    //p.name = "rose" name只读 不可改变
}