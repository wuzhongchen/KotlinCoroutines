class MagicBox3<T>(item: T) {
    var available = false
    private var subject: T = item

    //判断 魔盒可用的时候 才能
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

class Boy3(val name: String, val age: Int)

class Man3(val name: String, val age: Int)

class Dog3(val weight: Int)

fun main() {
    val box1: MagicBox3<Boy3> = MagicBox3(Boy3("Jack", 15))
    val box2: MagicBox3<Dog3> = MagicBox3(Dog3(20))
    box1.available = true

    box1.fetch()?.run {
        println("you find $name")
    }

    val man3 = box1.fetch( { boy3 ->
        Man3(boy3.name, boy3.age.plus(15))
    })

    val man4 = box1.fetch {
        Man3(it.name, it.age.plus(15))
    }
}
