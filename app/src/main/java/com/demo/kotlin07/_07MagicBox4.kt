//泛型类型约束
class MagicBox4<T : Human4>(item: T) {
    var available = false
    private var subject: T = item

    fun fetch(): T? {
        return subject.takeIf { available }
    }

    //业务，把元素进行修改
    //魔盒里面放的是男孩，取出来的时候，我给他改成了一个男人
    //return -> R
    fun <R> fetch(subjectModFunction: (T) -> R): R? {
        return subjectModFunction(subject).takeIf { available }
    }

}

open class Human4(val age: Int)

class Boy4(val name: String, age: Int) : Human4(age)

class Man4(val name: String, age: Int) : Human4(age)

class Dog4(val weight: Int)

fun main() {
    val box1: MagicBox4<Boy4> = MagicBox4(Boy4("Jack", 15))
    //val box2: MagicBox4<Dog4> = MagicBox4(Dog4(20))
    box1.available = true

    box1.fetch()?.run {
        println("you find $name")
    }

    val man = box1.fetch {
        Man4(it.name, it.age.plus(15))
    }
}
