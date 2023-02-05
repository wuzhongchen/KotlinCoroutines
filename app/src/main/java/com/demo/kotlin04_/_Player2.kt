class _Player2(
    _name: String,
    var age: Int, //取代 this.age = age 已经定义属性了
    var isNormal: Boolean //取代 this.isNormal = isNormal
) {

    var name = _name
        get() = field.capitalize()
        set(value) {
            field = value.trim()
        }

    //次构造函数
    constructor(name: String) : this(name, age = 10, isNormal = false)

    constructor(name: String, age: Int) : this(name, age = 10, isNormal = false){
        this.name = name.toUpperCase()
    }

}

fun main() {
    val p = _Player2("Jack", 20, true)
    //p.name = "rose"

    val p2 = _Player2("Rose")

    val p3 = _Player2("Jacky",20)
    println(p3.age)
}