class MagicBox6<T : Human6>(vararg item: T) {
    var available = false
    private var subject: Array<out T> = item

    fun fetch(index: Int): T? {
        return subject[index].takeIf { available }
    }

    //业务，把元素进行修改
    //魔盒里面放的是男孩，取出来的时候，我给他改成了一个男人
    //return -> R
    fun <R> fetch(index: Int, subjectModFunction: (T) -> R): R? {
        return subjectModFunction(subject[index]).takeIf { available }
    }

    operator fun get(index: Int): T? = subject[index]?.takeIf { available }
}

open class Human6(val age: Int)

class Boy6(val name: String, age: Int) : Human6(age)

class Man6(val name: String, age: Int) : Human6(age)

class Dog6(val weight: Int)

fun main() {
    val box1: MagicBox6<Boy6> = MagicBox6(
        Boy6("Jack", 15),
        Boy6("Jacky", 16),
        Boy6("John", 26)
    )
    box1.available = true

    box1.fetch(1)?.run {
        println("you find $name")
    }

    val man = box1.fetch(2) {
        Man6(it.name, it.age.plus(15))
    }

    //重载[] get
    val boy = box1[0]
    println("重载[] get")
}
