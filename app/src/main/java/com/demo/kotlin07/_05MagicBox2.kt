class MagicBox2<T>(item: T) {
    var available = false
    private var subject: T = item

    fun fetch():T?{
        return subject.takeIf { available }
    }

}

class Boy2(val name: String, val age: Int)

class Dog2(val weight: Int)

fun main() {
    val box1:MagicBox2<Boy2> = MagicBox2(Boy2("Jack",20))
    val box2:MagicBox2<Dog2> = MagicBox2(Dog2(20))
    box1.available = true

    box1.fetch()?.run {
        println("you find $name")
    }
}
