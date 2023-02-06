class MagicBox<T : Human>() {

    //随机产生一个对象，如果不是指定类型的对象，就通过backup函数生成一个指定类型的对象
//    fun <T> randomOrBackup1(
//        backup: () -> T
//    ): T {
//        val items = listOf(
//            Boy("Jack", 20),
//            Man("John", 35)
//        )
//        val random = items.shuffled().first()
//        return if(random is T){
//            random
//        }else{
//            backup()
//        }
//    }

    //reified关键字检查泛型参数类型
    inline fun <reified T> randomOrBackup2(backup: () -> T): T {
        val items = listOf(
            Boy("Jack", 20),
            Man("John", 35)
        )
        val random = items.shuffled().first()
        println(random)
        return if(random is T){
            random
        }else{
            backup()
        }
    }

}

open class Human(val age: Int)
class Boy(val name: String, age: Int) : Human(age){
    override fun toString(): String {
        return "Boy(name='$name',age='$age')"
    }
}
class Man(val name: String, age: Int) : Human(age){
    override fun toString(): String {
        return "Man(name='$name',age='$age')"
    }
}


fun main() {
    val box1:MagicBox<Man> = MagicBox()
    //又backup函数，推断出来T的类型
    val subject = box1.randomOrBackup2({
        Man("Jimmy_Backup", 38)
    }
    )
    println("==============================")
    println(subject)
}
