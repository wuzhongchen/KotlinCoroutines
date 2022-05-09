class Player3(
    _name: String,
    var age: Int = 20,
    var isNormal: Boolean
) {

    var name = _name
        get() = field.capitalize()
        set(value) {
            field = value.trim()
        }

    constructor(name: String) : this(name, age = 10, isNormal = false)

    constructor(name: String, age: Int) : this(name, age = 10, isNormal = false){
        this.name = name.toUpperCase()
    }

    //初始化 代码块 构造类实例时执行，和静态代码块不同
    init {
        require(age > 0){"age muse be positive"}
        require(name.isNotBlank()) {"player must have a name."}
    }

}

fun main() {
    Player3(isNormal =  false,_name ="Jack")

}