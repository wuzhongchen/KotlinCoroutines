//可变参数
class MagicBox5<T : Human5>(vararg item: T) {
    var available = false
    //out
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
}

open class Human5(val age: Int)

class Boy5(val name: String, age: Int) : Human5(age)

class Man5(val name: String, age: Int) : Human5(age)

class Dog(val weight: Int)

fun main() {
    val box1: MagicBox5<Boy5> = MagicBox5(
        Boy5("Jack", 15),
        Boy5("Jacky", 16),
        Boy5("John", 26)
    )
    box1.available = true

    box1.fetch(1)?.run {
        println("you find $name")
    }
    val man1 = box1.fetch(2, { boy5 ->
        Man5(boy5.name, boy5.age.plus(15))
    })


    val man2 = box1.fetch(2) {
        Man5(it.name, it.age.plus(15))
    }
}
